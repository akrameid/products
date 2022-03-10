package com.akram.product.validation;


import com.akram.product.exception.*;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        Map<String, String> errors = new HashMap<>();
        if (mostSpecificCause instanceof UnrecognizedPropertyException) {
            UnrecognizedPropertyException cause = (UnrecognizedPropertyException) mostSpecificCause;
            String propertyName = cause.getPropertyName();
            String message = "This property is not expected in the request body.";
            errors.put(propertyName, message);
            return new ResponseEntity(errors, headers, status);
        } else {
            String exceptionName = "Error in the request";
            String message = mostSpecificCause.getMessage();
            errors.put(exceptionName, message);
            return new ResponseEntity(exceptionName, headers, status);
        }
    }


    @ResponseBody
    @ExceptionHandler(CustomerNameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String customerNameFoundException(CustomerNameNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(CustomerNameExistedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String customerNameExistedException(CustomerNameExistedException ex) {
        return ex.getMessage();
    }


    @ResponseBody
    @ExceptionHandler(ProductNameFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String productNameFoundException(ProductNameFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductIdNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String productIdNotFoundException(ProductIdNotFoundException ex) {
        return ex.getMessage();
    }
    @ResponseBody
    @ExceptionHandler(CustomerIdNotFoundException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    String customerIdNotFoundException(CustomerIdNotFoundException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ProductBalanceNotEnoughException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String productBalanceNotEnoughException(ProductBalanceNotEnoughException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(CustomerCreditNotEnoughException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String customerCreditNotEnoughException(CustomerCreditNotEnoughException ex) {
        return ex.getMessage();
    }

}