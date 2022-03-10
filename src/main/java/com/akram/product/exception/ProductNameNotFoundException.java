package com.akram.product.exception;

public class ProductNameNotFoundException extends RuntimeException {
    public ProductNameNotFoundException(String name){
        super("Product name \""+name+"\" is not existed in the database");
    }
}
