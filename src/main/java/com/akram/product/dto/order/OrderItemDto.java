package com.akram.product.dto.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class OrderItemDto {
    private Long productId;
    private String productName;
    private Long productPrice;
    private Integer count;
}
