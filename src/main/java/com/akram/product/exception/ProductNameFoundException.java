package com.akram.product.exception;

public class ProductNameFoundException extends RuntimeException {
    public ProductNameFoundException(String name){
        super("Product name \""+name+"\" is existed in the database");
    }
}
