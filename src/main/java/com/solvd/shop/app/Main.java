package com.solvd.shop.app;

import com.solvd.shop.enums.DiscountCodes;
import com.solvd.shop.enums.PaymentMethod;
import com.solvd.shop.model.Payment;
import com.solvd.shop.model.*;
import com.solvd.shop.service.Shop;
import com.solvd.shop.service.ShopRating;
import com.solvd.shop.exceptions.*;
import com.solvd.shop.funtionalInterfaces.InventoryAdjuster;
import com.solvd.shop.funtionalInterfaces.PaymentVerifier;
import com.solvd.shop.funtionalInterfaces.ShippingEstimator;
import com.solvd.shop.singleton.LoginManager;
import com.solvd.shop.threads.User1Thread;
import com.solvd.shop.threads.User2Thread;
import com.solvd.shop.threads.User3Thread;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        final Logger LOGGER = LogManager.getLogger(Main.class);
        Scanner scanner = new Scanner(System.in);

        Inventory inventory = new Inventory();

        Vector<ShopRating> feedbackHistory = new Vector<>();

        DiscountCodes discountCode = DiscountCodes.TEN_OFF;
        int code = discountCode.getDiscountValue();

        Map<String, Category> categories = new HashMap<>();
        categories.put("Electronics", new Category("Electronics"));
        categories.put("Accesories", new Category("Accesories"));

        Category electronics = categories.get("Electronics");
        Category accesories = categories.get("Accesories");

        Set<Object[]> categoryIdSet = new LinkedHashSet<>();
        categoryIdSet.add(new Object[]{electronics, 23443234L});
        categoryIdSet.add(new Object[]{accesories, 56767675L});

        categoryIdSet.stream().forEach(pair -> {
            Category category = (Category) pair[0];
            Long id = (Long) pair[1];
            category.setId(id);
        });

        inventory.addProduct(new Product("Cellphone Gen 26", 1100, electronics));
        inventory.addProduct(new Product("Laptop Gen 26", 1950, electronics));
        inventory.addProduct(new Product("Laptop Case", 29.99, accesories));
        inventory.addProduct(new Product("Deluxe Laptop Case", 39.99, accesories));
        inventory.addProduct(new Product("Retro Headphones", 499, electronics));

        InventoryAdjuster adjuster = (inv, product, delta) -> {
            if (delta > 0) {
                for (int i = 0; i < delta; i++) {
                    inv.addProduct(product);
                }
                return inv.getAvailableProducts().size();
            } else {
                return inv.getAvailableProducts().size();
            }
        };

        PaymentVerifier verifier = (payment, amount) -> {
            return payment != null && amount > 0;
        };

        ShippingEstimator estimator = (origin, destination, weightKg) -> {
            if (origin.getCity().equalsIgnoreCase(destination.getCity())) {
                return 5.0 + (weightKg * 0.5);
            } else {
                return 15.0 + (weightKg * 1.2);
            }
        };

        LOGGER.info("What's your name?");
        String name = scanner.nextLine();

        Address address = null;
        while (address == null) {
            try {
                LOGGER.info("Please enter your street");
                String street = scanner.nextLine();

                if (street.length() < 5) {
                    throw new InvalidAddressException("Street must have at least 5 characters.");
                }

                LOGGER.info("Please enter your city");
                String city = scanner.nextLine();

                if (city.length() < 3) {
                    throw new InvalidAddressException("City must have at least 3 characters.");
                }

                address = new Address(street, city);

            } catch (InvalidAddressException e) {
                LOGGER.error(e.getMessage());
            }
        }

        int typeOfUser = -1;
        while (true) {
            LOGGER.info("Type/Write only the number according to the person you are: Customer (2), VIP Customer (3), Admin (4), Cashier (5)");
            try {
                if (!scanner.hasNextInt()) {
                    throw new NotAnIntegerException("You must enter a valid integer number.");
                }
                typeOfUser = scanner.nextInt();
                if (typeOfUser <= 0) {
                    throw new NegativeOrZeroIntegerException("The number must be positive.");
                }
                if (typeOfUser != 2 && typeOfUser != 3 && typeOfUser != 4 && typeOfUser != 5) {
                    throw new OutOfRangeOptionException("Invalid option. Choose 2, 3, 4, or 5.");
                }
                break;
            } catch (NotAnIntegerException | NegativeOrZeroIntegerException | OutOfRangeOptionException e) {
                LOGGER.error(e.getMessage());
                scanner.next();
            }
        }

        Customer customer = new Customer(name, address, typeOfUser);
        Admin admin = new Admin(name, typeOfUser);
        VipUser vipUser = new VipUser(name, typeOfUser);
        StandardUser standardUser = new StandardUser(name, typeOfUser);

        Shop shop = new Shop(inventory);
        Cart cart = new Cart();

        while (cart.getProducts().isEmpty()) {
            shop.showProducts();
            LOGGER.info("Select product number to add to cart (Type 0 to finish adding items and go to the Checkout):");

            int choice;
            while (true) {
                try {
                    if (!scanner.hasNextInt()) {
                        throw new NotAnIntegerException("You must enter a valid integer number (Type 0 to finish adding items and go to the Checkout).");
                    }
                    choice = scanner.nextInt();
                    if (choice < 0) {
                        throw new NegativeOrZeroIntegerException("Write the number of a product from 1-5");
                    }
                    scanner.nextLine();
                    if (choice == 0) break;
                    Product selected = shop.getProductByIndex(choice - 1);
                    if (selected != null) {
                        cart.addProduct(selected);
                        LOGGER.info("Added to cart: " + selected.getName());

                    } else {
                        throw new OutOfRangeOptionException("Invalid product number. Write the number of a product from 1-5\"");
                    }
                    LOGGER.info("Select another product (0 to finish):");
                } catch (NotAnIntegerException | NegativeOrZeroIntegerException | OutOfRangeOptionException e) {
                    LOGGER.error(e.getMessage());
                    scanner.next();
                }
            }

            if (cart.getProducts().isEmpty()) {
                LOGGER.error("Your cart is empty. Please add a product before checkout.");
            }
        }

        try {
            if (cart.getProducts().isEmpty()) {
                throw new ProductNotInCartException("Your cart is empty. Please add a product before checkout.");
            }
        } catch (ProductNotInCartException e) {
            LOGGER.error(e.getMessage());
            return;
        }

        int paymentMethodChosen = -1;
        while (true) {
            LOGGER.info("Please select your payment method: 1 for cash or 2 for card");
            try {
                if (!scanner.hasNextInt()) {
                    throw new NotAnIntegerException("You must enter 1 or 2.");
                }
                paymentMethodChosen = scanner.nextInt();
                if (paymentMethodChosen <= 0) {
                    throw new NegativeOrZeroIntegerException("Number must be positive.");
                }
                if (paymentMethodChosen != 1 && paymentMethodChosen != 2) {
                    throw new OutOfRangeOptionException("Invalid choice. Enter 1 or 2.");
                }
                break;
            } catch (NotAnIntegerException | NegativeOrZeroIntegerException | OutOfRangeOptionException e) {
                LOGGER.error(e.getMessage());
                scanner.next();
            }
        }

        Cash cash = new Cash("cash");
        Card card = new Card("card");

        if (paymentMethodChosen == 1) {
            cash.getPaymentConfirmation();
        } else {
            card.getPaymentConfirmation();
        }

        PaymentMethod method = (paymentMethodChosen == 1) ? PaymentMethod.CASH : PaymentMethod.CREDIT_CARD;
        Order order = new Order(customer, cart.getProducts(), method);

        if (typeOfUser == 5) {
            admin.getPriority();
            LOGGER.info("As an admin, you get priority level " + admin.getPriority() + " and " + customer.getId());
            inventory.restock();
        } else if (typeOfUser == 4) {
            LOGGER.info("As a VipUser, you get priority level " + vipUser.getPriority());
        } else if (typeOfUser == 3) {
            LOGGER.info("As a StandardUser, you get priority level " + standardUser.getPriority());
        } else {
            LOGGER.info("As a Customer, you get priority level " + customer.getPriority());
        }

        double originalTotal = cart.getTotal();
        LOGGER.info("\nCart: $" + originalTotal);

        Discount summerSale = new Discount("SUMMER10", code, 500.0,
                "10% off on orders over $500");
        double discountedTotal = summerSale.applyIfEligible(originalTotal);

        if (discountedTotal < originalTotal) {
            System.out.printf("Discount '%s' applied: %s%nOriginal: $%.2f -> Total Cart After Discount: $%.2f%n",
                    summerSale.getCode(),
                    summerSale.getDescription(),
                    originalTotal,
                    discountedTotal);
        } else {
            LOGGER.info("No discount applied since your order is below $500 USD.");
        }

        order.setTotal(discountedTotal);
        Payment chosenPayment = (paymentMethodChosen == 1) ? cash : card;
        ConfirmationMessage confirmationMessage = new ConfirmationMessage((String) chosenPayment.getPaymentConfirmation());

        if (confirmationMessage.processPayment(order.getTotal())) {
            LOGGER.info("Order placed successfully for " + name + "! " + customer.getRecommendation());
        }

        LOGGER.info(order);

        int rating;
        while (true) {
            LOGGER.info("Hi " + name + "! How would you rate our shop from 1 to 10?");
            try {
                if (!scanner.hasNextInt()) {
                    throw new NotAnIntegerException("You must enter a number between 1 and 10.");
                }
                rating = scanner.nextInt();
                if (rating <= 0) {
                    throw new NegativeOrZeroIntegerException("Please type a positive number.");
                }
                if (rating > 10) {
                    throw new OutOfRangeOptionException("Rating number must be between 1 and 10.");
                }
                break;
            } catch (NotAnIntegerException | NegativeOrZeroIntegerException | OutOfRangeOptionException e) {
                LOGGER.error(e.getMessage());
                scanner.next();
            }
        }
        scanner.nextLine();

        LOGGER.info("Please, leave a comment for us");
        String comment = scanner.nextLine();

        ShopRating shopRating = new ShopRating(rating, comment);
        feedbackHistory.add(shopRating);

        LOGGER.info("Thanks for your rating of: " + shopRating.getRating() + " and for your sincere comment: << " + shopRating.getComments() + " >>. This helps us to improve!");

        scanner.close();

        String inputFilePath = "src/main/resources/input.txt";
        String outputFilePath = "src/main/resources/output.txt";
        SpecialWordCounter.countAndWrite(inputFilePath, outputFilePath);

        User1Thread t1 = new User1Thread();
        t1.setName("Thread-1");

        User2Thread t2 = new User2Thread();
        t2.setName("Thread-2");

        User3Thread t3 = new User3Thread();
        t3.setName("Thread-3");

        t1.start();
        t2.start();
        t3.start();
    }
}

