package com.solvd.shop.model;

import com.solvd.shop.interfaces.Identifiable;

public class Discount implements Identifiable <Long> {

    private Long id;
    private String code;
    private int percentage;
    private double minimumPurchase;
    private String description;

    public Discount(String code, int percentage, double minimumPurchase, String description) {
        setCode(code);
        setPercentage(percentage);
        setMinimumPurchase(minimumPurchase);
        setDescription(description);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        if (code == null || code.isBlank()) {
            throw new IllegalArgumentException("Code cannot be null or empty");
        }
        this.code = code;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100");
        }
        this.percentage = percentage;
    }

    public double getMinimumPurchase() {
        return minimumPurchase;
    }

    public void setMinimumPurchase(double minimumPurchase) {
        if (minimumPurchase < 0) {
            throw new IllegalArgumentException("Minimum purchase cannot be negative");
        }
        this.minimumPurchase = minimumPurchase;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null) {
            description = "";
        }
        this.description = description;
    }

    public double applyIfEligible(double total) {
        if (total >= minimumPurchase) {
            double discountAmount = total * (percentage / 100.0);
            return total - discountAmount;
        }
        return total;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
