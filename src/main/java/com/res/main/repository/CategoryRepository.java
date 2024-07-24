package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoriesEntity, Long> {
}
