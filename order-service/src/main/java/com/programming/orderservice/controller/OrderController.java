package com.programming.orderservice.controller;


import com.programming.orderservice.dto.Request.OrderRequest;
import com.programming.orderservice.dto.Response.OrderResponse;
import com.programming.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

    private final OrderService orderService;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @GetMapping
    public List<OrderResponse> getOrders()
    {
        return orderService.getOrdersWithItems();
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public String placeOrder(@RequestBody OrderRequest orderRequest)
    {
        orderService.placeOrder(orderRequest);
        return "Order Placed successfully";
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @DeleteMapping("/{id}")
    public String deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return "Order removed successfully";
    }

//    @ResponseStatus(HttpStatus.ACCEPTED)
//    @PutMapping("/{id}")
//    public OrderResponse updateOrder(@PathVariable Long id , @RequestBody OrderRequest orderRequest){
//        orderService.updateOrder(id , orderRequest);
//    }

}
