package com.akram.product.exception;

public class ProductBalanceNotEnoughException extends RuntimeException {
    public ProductBalanceNotEnoughException(String productName, int count){
        super("There is no stock available for product \""+productName+"\", and the required quantity is "+count);
    }
}
