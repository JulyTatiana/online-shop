package com.shop.model;

import com.shop.interfaces.Returnable;

import java.util.ArrayList;
import java.util.List;

public class Cart implements Returnable {

    private List<Product> products;

    public Cart() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = new ArrayList<>(products);
    }

    public double getTotal() {
        return products.stream().mapToDouble(Product::getPrice).sum();
    }

    @Override
    public boolean returnable() {
        return !products.isEmpty() && getTotal() > 0;
    }

}