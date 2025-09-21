package com.solvd.shop.model;

public abstract class Addressable {

    protected Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
