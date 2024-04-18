package com.programming.orderservice.repository;

import com.programming.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderLineItems, Long> {

}
