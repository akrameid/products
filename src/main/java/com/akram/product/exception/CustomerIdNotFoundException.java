package com.akram.product.exception;

public class CustomerIdNotFoundException extends RuntimeException {
    public CustomerIdNotFoundException(Long id){
        super("Customer id \""+ id +"\" is not found in the database");
    }
}
