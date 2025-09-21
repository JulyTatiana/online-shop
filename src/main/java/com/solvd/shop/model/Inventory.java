package com.solvd.shop.model;

import com.solvd.shop.interfaces.Restocking;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Restocking {

    private static final Logger  LOGGER = LogManager.getLogger(Inventory.class);
    private List<Product> availableProducts;

    public Inventory() {
        this.availableProducts = new ArrayList<>();
    }

    public void addProduct(Product product) {
        availableProducts.add(product);
    }

    public List<Product> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(List<Product> availableProducts) {
        this.availableProducts = availableProducts;
    }

    @Override
    public void restock() {
         LOGGER.warn("It's needed to evaluate restocking: " + availableProducts);
    }
}