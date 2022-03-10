package com.akram.product.controller;

import com.akram.product.dto.customer.AddCustomerRequestDto;
import com.akram.product.dto.customer.CustomerDto;
import com.akram.product.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService service;
    @GetMapping()
    ResponseEntity<List<CustomerDto>> getAll() {
        return new ResponseEntity<List<CustomerDto>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<String> addCustomer(@Valid @RequestBody AddCustomerRequestDto addCustomerRequestDto) {
        return new ResponseEntity<String>(service.addCustomer(addCustomerRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.delete(id), HttpStatus.OK);
    }
}
