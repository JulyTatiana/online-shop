package com.solvd.shop.model;

public class Card extends Payment<String> {

    private String cardPayment;

    public Card (String cardPayment){
        this.cardPayment=cardPayment;
    }

    public String getCardPayment() {
        return cardPayment;
    }

    public void setCardPayment(String cardPayment) {
        this.cardPayment = cardPayment;
    }

    @Override
    public String getPaymentConfirmation(){
        return "Credit Card";
    }

}
