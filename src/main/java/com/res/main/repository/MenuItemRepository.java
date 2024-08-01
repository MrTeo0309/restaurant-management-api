package com.res.main.repository;

import com.res.main.model.MenuItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItemsEntity, Long> {
}
