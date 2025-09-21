package com.solvd.shop.enums;

public enum DiscountCodes {
    TEN_OFF(10), FIFTEEN_OFF(15), TWENTY_OFF(20), THIRTY_OFF(30);

    private final int discountValue;


    DiscountCodes(int discountValue) {
        this.discountValue=discountValue;
    }

    public int getDiscountValue(){
        return discountValue;
    }
}
