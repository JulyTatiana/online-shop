package com.solvd.shop.funtionalInterfaces;

import com.solvd.shop.model.Payment;

@FunctionalInterface
public interface PaymentVerifier {
    boolean verify(Payment<?> payment, double amount);
}
