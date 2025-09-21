package com.solvd.shop.service;

import com.solvd.shop.model.Satisfaction;

public class ShopRating <T> extends Satisfaction {
    private T rating;

    public ShopRating(T rating, String comments) {
        super(comments);
        this.rating=rating;
    }

    public T getRating() {
        return rating;
    }

    public void setRating(T rating) {
        this.rating = rating;
    }
}
