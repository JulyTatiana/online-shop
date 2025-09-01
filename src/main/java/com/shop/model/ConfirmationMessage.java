package com.shop.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ConfirmationMessage {

    private static final Logger LOGGER = LogManager.getLogger(ConfirmationMessage.class);
    private String method;

    public ConfirmationMessage(String method) {
        this.method = method;
    }

    public boolean processPayment(double amount) {
        LOGGER.info("Your payment of $" + amount + " USD via " + method + " was approved.");
        return true;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "ConfirmationMessage{method='" + method + "'}";
    }

}
