package com.res.main.repository;

import com.res.main.model.CategoriesEntity;
import com.res.main.model.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeesEntity, Long> {
    Optional<EmployeesEntity> findByName(String name);
}
