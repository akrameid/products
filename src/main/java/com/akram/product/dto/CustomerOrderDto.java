package com.akram.product.dto;

import com.akram.product.dto.order.OrderItemDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CustomerOrderDto {
    private Long id;
    private String customerName;
    private Long totalPrice;
    private List<OrderItemDto> orderDetails;
}
