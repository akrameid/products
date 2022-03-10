package com.akram.product.dto.customer;

import com.akram.product.dto.order.OrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CustomerDto {
    private long id;
    private String name;
    private Long creditLimit;
    private Long currentCredit;
    private List<OrderDto> orderDtos;
}
