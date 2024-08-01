package com.res.main.repository;

import com.res.main.model.CartEntity;
import com.res.main.model.CartItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItemsEntity, Long> {
}
