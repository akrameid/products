package com.akram.product.dto.customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddCustomerRequestDto {
    private String customerName;
    private Long creditLimit;
    private Long currentCredit;
}
