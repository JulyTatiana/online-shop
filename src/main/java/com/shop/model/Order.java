package com.shop.model;

import com.shop.enums.OrderStatus;
import com.shop.enums.PaymentMethod;
import com.shop.interfaces.Shippable;

import java.util.List;

public class Order extends Payment <String> implements Shippable {

    private OrderStatus status;
    private Customer customer;
    private List<Product> products;
    private double total;
    private PaymentMethod paymentMethod;

    public Order(Customer customer, List<Product> products, PaymentMethod paymentMethod) {
        this.customer = customer;
        this.products = products;
        this.total = products.stream().mapToDouble(Product::getPrice).sum();
        this.paymentMethod = paymentMethod;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{customer=" + customer +
                ", products=" + products +
                ", total=" + total +
                ", status=" + status +
                ", paymentMethod=" + paymentMethod + '}';
    }

    @Override
    public Address getShippingAddress() {
        return null;
    }

    @Override
    public void setShippingAddress(Address address) {
    }

    @Override
    public String getPaymentConfirmation() {
        return "Order Payment Processed";
    }
}
