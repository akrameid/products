package com.akram.product.exception;

public class CustomerCreditNotEnoughException extends RuntimeException{
    public CustomerCreditNotEnoughException(String customerName, long requiredCredit, long currentCredit){
        super("Customer \""+customerName+"\" does not have enough credit for paying "+requiredCredit +". The current credit is "+currentCredit);
    }
}
