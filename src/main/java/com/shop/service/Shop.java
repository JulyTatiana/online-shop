package com.shop.service;

import com.shop.model.Product;
import com.shop.model.Inventory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Shop {

    private static final Logger LOGGER = LogManager.getLogger(Shop.class);
    private Inventory inventory;

    public Shop(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        if (inventory == null) {
            throw new IllegalArgumentException("Inventory cannot be null");
        }
        this.inventory = inventory;
    }

    public void showProducts() {
        List<Product> products = inventory.getAvailableProducts();
        LOGGER.info("AVAILABLE PRODUCTS:");
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            LOGGER.info((i + 1) + ". " + p.getName() + " - $" + p.getPrice());
        }
    }

    public Product getProductByIndex(int index) {
        List<Product> products = inventory.getAvailableProducts();
        if (index >= 0 && index < products.size()) {
            return products.get(index);
        }
        return null;
    }
}