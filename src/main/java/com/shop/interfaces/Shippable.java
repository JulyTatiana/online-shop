package com.shop.interfaces;

import com.shop.model.Address;

public interface Shippable {
    Address getShippingAddress();
    void setShippingAddress(Address address);
}
