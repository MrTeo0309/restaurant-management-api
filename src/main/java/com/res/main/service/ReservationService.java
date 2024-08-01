package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.ReservationsEntity;
import com.res.main.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public ApiResponse<List<ReservationsEntity>> getAllReservations() {
        List<ReservationsEntity> reservations = reservationRepository.findAll();
        if (reservations.isEmpty()) {
            return new ApiResponse<>(false, "No reservations found", reservations);
        }
        return new ApiResponse<>(true, "Reservations retrieved successfully", reservations);
    }

    public ApiResponse<ReservationsEntity> createReservation(ReservationsEntity reservation) {
        ReservationsEntity savedReservation = reservationRepository.save(reservation);
        return new ApiResponse<>(true, "Reservation created successfully", savedReservation);
    }

    public ApiResponse<ReservationsEntity> getReservationById(long id) {
        Optional<ReservationsEntity> reservation = reservationRepository.findById(id);
        if (reservation.isPresent()) {
            return new ApiResponse<>(true, "Reservation retrieved successfully", reservation.get());
        }
        return new ApiResponse<>(false, "Reservation not found", null);
    }

    public ApiResponse<ReservationsEntity> updateReservation(long id, ReservationsEntity updatedReservation) {
        if (reservationRepository.existsById(id)) {
            updatedReservation.setId(id);
            ReservationsEntity savedReservation = reservationRepository.save(updatedReservation);
            return new ApiResponse<>(true, "Reservation updated successfully", savedReservation);
        }
        return new ApiResponse<>(false, "Reservation not found", null);
    }

    public ApiResponse<String> deleteReservation(long id) {
        if (reservationRepository.existsById(id)) {
            reservationRepository.deleteById(id);
            return new ApiResponse<>(true, "Reservation deleted successfully", "Reservation deleted");
        }
        return new ApiResponse<>(false, "Reservation not found", null);
    }
}
