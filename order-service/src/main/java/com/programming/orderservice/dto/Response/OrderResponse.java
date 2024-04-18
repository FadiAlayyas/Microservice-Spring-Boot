package com.programming.orderservice.dto.Response;

import com.programming.orderservice.dto.Response.OrderLineItemDtoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponse{

    private String id;
    private String order_number;
    private List<OrderLineItemDtoResponse> orderLineItemsList;
}
