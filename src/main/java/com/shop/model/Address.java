package com.shop.model;

import com.shop.interfaces.Trackeable;

public record Address(String street, String city) implements Trackeable {

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

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
