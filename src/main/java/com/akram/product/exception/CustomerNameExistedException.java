package com.akram.product.exception;

public class CustomerNameExistedException extends RuntimeException {
    public CustomerNameExistedException(String name){
        super("Customer name \""+name+"\" is existed in the database");
    }
}
