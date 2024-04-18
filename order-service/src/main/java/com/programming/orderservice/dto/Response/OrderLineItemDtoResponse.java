package com.programming.orderservice.dto.Response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderLineItemDtoResponse{

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
