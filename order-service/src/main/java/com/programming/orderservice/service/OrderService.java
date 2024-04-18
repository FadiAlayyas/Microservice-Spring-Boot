package com.programming.orderservice.service;

import com.programming.orderservice.dto.Response.OrderLineItemDtoResponse;
import com.programming.orderservice.dto.Request.OrderLineItemsDto;
import com.programming.orderservice.dto.Request.OrderRequest;
import com.programming.orderservice.dto.Response.OrderResponse;
import com.programming.orderservice.model.Order;
import com.programming.orderservice.model.OrderLineItems;
import com.programming.orderservice.repository.OrderItemRepository;
import com.programming.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderItemRepository orderItemRepository;

    public List<OrderResponse> getOrdersWithItems()
    {
        List<Order> orders = orderRepository.findAll();

        return orders.stream().map(this::mapToOrderResponse).toList();

    }
    public void placeOrder(OrderRequest orderRequest)
    {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderListItem = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(orderLineItemDto -> mapOrderLineItemsToDto(orderLineItemDto, order))
                .toList();

        order.setOrderLineItemsList(orderListItem);
        orderRepository.save(order);
    }


    public void deleteOrder(Long $id)
    {
        orderRepository.deleteById($id);
    }

    private OrderLineItems mapOrderLineItemsToDto(OrderLineItemsDto orderLineItemsDto , Order order)
    {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setOrder(order);
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return orderLineItems;
    }


    private OrderResponse mapToOrderResponse(Order order)
    {
        List<OrderLineItemDtoResponse> orderLineItemsDtoList =
                order.getOrderLineItemsList() != null
                        ? order.getOrderLineItemsList().stream().map(this::mapToOrderItemResponse).toList()
                        : Collections.emptyList();


        return OrderResponse.builder()
                .id(String.valueOf(order.getId()))
                .order_number(order.getOrderNumber())
                .orderLineItemsList(orderLineItemsDtoList)
                .build();
    }

    private OrderLineItemDtoResponse mapToOrderItemResponse(OrderLineItems orderLineItems)
    {
         return OrderLineItemDtoResponse.builder()
                 .id(orderLineItems.getId())
                 .skuCode(orderLineItems.getSkuCode())
                 .quantity(orderLineItems.getQuantity())
                 .price(orderLineItems.getPrice())
                 .build();
    }
}
