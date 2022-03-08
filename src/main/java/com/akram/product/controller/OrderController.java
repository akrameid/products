package com.akram.product.controller;

import com.akram.product.dto.CustomerOrder;
import com.akram.product.dto.createorder.Request;
import com.akram.product.dto.createorder.Response;
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
    ResponseEntity<List<CustomerOrder>> getAllOrders() {
        return new ResponseEntity<List<CustomerOrder>>(service.getAll(), HttpStatus.OK);
    }
//
//    @Operation(
//            summary = "Create RIG", description = "Create RIG", tags = {"RIGs"}
//            , responses =
//            {
//                    @ApiResponse(description = "Success", responseCode = "201",
//                            content = @Content(mediaType = "application/json",schema = @Schema(implementation = RigDto.class))
//                    )}
//    )
    @PostMapping("/create")
    ResponseEntity<Response> addRig(@Valid @RequestBody Request request) {
        return new ResponseEntity<Response>(service.createOrder(request), HttpStatus.CREATED);
    }
}
