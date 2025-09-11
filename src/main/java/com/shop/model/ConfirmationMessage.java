package com.shop.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public record ConfirmationMessage(String method) {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmationMessage.class);

    public boolean processPayment(double amount) {
        LOGGER.info("Your payment of $" + amount + " USD via " + method + " was approved.");
        return true;
    }

    @Override
    public String toString() {
        return "ConfirmationMessage{method='" + method + "'}";
    }
}

