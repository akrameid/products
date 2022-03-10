package com.akram.product.exception;

public class ProductIdNotFoundException extends RuntimeException {
    public ProductIdNotFoundException(Long id){
        super("Product id \""+ id +"\" is not found in the database");
    }
}
