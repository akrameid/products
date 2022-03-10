package com.akram.product.exception;

public class CustomerNameNotFoundException extends RuntimeException {
    public CustomerNameNotFoundException(String name){
        super("Customer name \""+name+"\" is not existed in the database");
    }
}
