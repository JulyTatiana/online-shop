package com.shop.funtionalInterfaces;

import com.shop.model.Payment;

@FunctionalInterface
public interface PaymentVerifier {
    boolean verify(Payment<?> payment, double amount);
}
