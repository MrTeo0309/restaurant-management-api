package com.res.main.repository;

import com.res.main.model.TablesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TableRepository extends JpaRepository<TablesEntity, Long> {
}