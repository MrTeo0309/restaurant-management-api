package com.res.main.repository;

import com.res.main.model.ReservationsEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<ReservationsEntity, Long> {
}
