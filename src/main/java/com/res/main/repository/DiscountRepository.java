package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import com.res.main.model.DiscountsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountRepository extends JpaRepository<DiscountsEntity, Long> {
}
