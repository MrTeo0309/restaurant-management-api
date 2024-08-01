package com.res.main.repository;

import com.res.main.model.ShiftsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<ShiftsEntity, Long> {
}
