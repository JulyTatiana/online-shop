package com.shop.funtionalInterfaces;

import com.shop.model.Address;

@FunctionalInterface
public interface ShippingEstimator {
    double estimate(Address origin, Address destination, double weightKg);
}
