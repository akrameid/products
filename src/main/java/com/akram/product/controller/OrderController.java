package com.akram.product.controller;

import com.akram.product.dto.CustomerOrderDto;
import com.akram.product.dto.order.CreateOrderRequestDto;
import com.akram.product.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService service;
    @GetMapping()
    ResponseEntity<List<CustomerOrderDto>> getAllOrders() {
        return new ResponseEntity<List<CustomerOrderDto>>(service.getAll(), HttpStatus.OK);
    }
    @PostMapping()
    ResponseEntity<String> createOrder(@Valid @RequestBody CreateOrderRequestDto createOrderRequestDto) {
        return new ResponseEntity<String>(service.createOrder(createOrderRequestDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String> delete(@PathVariable Long id){
        return new ResponseEntity<String>(service.delete(id),HttpStatus.OK);
    }
}
