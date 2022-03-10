package com.akram.product.dto.product;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@JsonIgnoreProperties()
public class ProductDto {

    @Setter(AccessLevel.PRIVATE)
    @Schema(hidden = true)
    private long id;
    private String name;
    private Long price;
    private Integer balance;
}
