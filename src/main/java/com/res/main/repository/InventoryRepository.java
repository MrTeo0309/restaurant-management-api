package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import com.res.main.model.InventoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryEntity, Long> {
}
