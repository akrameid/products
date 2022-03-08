package com.akram.product.dto.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Request {
    private String customerName;
    private List<OrderItem> orderDetails;
}
