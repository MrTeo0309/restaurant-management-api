package com.res.main.repository;

import com.res.main.model.DiscountsEntity;
import com.res.main.model.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<DishesEntity, Long> {
}
