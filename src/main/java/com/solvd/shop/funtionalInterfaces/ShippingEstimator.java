package com.solvd.shop.funtionalInterfaces;

import com.solvd.shop.model.Address;

@FunctionalInterface
public interface ShippingEstimator {
    double estimate(Address origin, Address destination, double weightKg);
}
