package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import com.res.main.model.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SupplierRepository extends JpaRepository<SuppliersEntity, Long> {
    Optional<SuppliersEntity> findByName(String Name);
}
