package com.solvd.shop.interfaces;

import com.solvd.shop.model.Address;

public interface Shippable {
    Address getShippingAddress();
    void setShippingAddress(Address address);
}
