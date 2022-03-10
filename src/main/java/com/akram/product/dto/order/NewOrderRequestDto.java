package com.akram.product.dto.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewOrderRequestDto {
    private String customerName;
    private List<OrderItemDto> orderDetails;
}
