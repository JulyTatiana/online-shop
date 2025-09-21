package com.solvd.shop.model;

public class Cash extends Payment <String> {

    private String cashPayment;

    public Cash (String cashPayment){
        this.cashPayment=cashPayment;
    }

    public String getCashPayment() {
        return cashPayment;
    }

    public void setCashPayment(String cashPayment) {
        this.cashPayment = cashPayment;
    }

    @Override
    public String getPaymentConfirmation(){
        return "Cash";
    }

}