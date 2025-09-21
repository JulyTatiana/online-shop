package com.solvd.shop.model;

import com.solvd.shop.interfaces.Trackeable;

public record Address(String street, String city) implements Trackeable {

    public String getCity() {
        return city;
    }

    @Override
    public String getStatus() {
        return "Your package will arrive to Street: " + street + " in " + city + " City";
    }

    @Override
    public void setStatus(String status) {
    }
}
