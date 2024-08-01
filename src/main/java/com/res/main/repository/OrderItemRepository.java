package com.res.main.repository;

import com.res.main.model.OrderItemsEntity;
import com.res.main.model.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItemsEntity, Long> {
}
