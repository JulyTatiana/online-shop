package com.shop.model;

import com.shop.interfaces.Trackeable;

public class Address implements Trackeable {

    private String street;
    private String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getStatus() {
        return "Your package will arrive to Street: " + street + " in " + city + " City";
    }

    @Override
    public void setStatus(String status) {

    }
}