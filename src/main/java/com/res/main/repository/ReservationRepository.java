package com.res.main.repository;

import com.res.main.model.ReservationsEntity;
import com.res.main.model.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<ReservationsEntity, Long> {
}