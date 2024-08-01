package com.res.main.repository;

import com.res.main.model.CustomersEntity;
import com.res.main.model.DishesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomersEntity, Long> {
    Optional<CustomersEntity>   findByEmail(String email);
}
