package com.akram.product.controller;

import com.akram.product.dto.customer.AddCustomerRequestDto;
import com.akram.product.dto.customer.CustomerDto;
import com.akram.product.dto.product.ProductDto;
import com.akram.product.service.CustomerService;
import com.akram.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService service;
    @GetMapping()
    ResponseEntity<List<ProductDto>> getAll() {
        return new ResponseEntity<List<ProductDto>>(service.getAll(), HttpStatus.OK);
    }

    @PostMapping()
    ResponseEntity<String> addProduct(@Valid @RequestBody ProductDto productDto) {
        return new ResponseEntity<String>(service.addProduct(productDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id) {
        return new ResponseEntity<String>(service.delete(id), HttpStatus.OK);
    }
}
