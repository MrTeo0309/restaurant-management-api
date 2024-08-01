package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.ReservationsEntity;
import com.res.main.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ResponseEntity<?> getAllReservations() {
        ApiResponse<List<ReservationsEntity>> response = reservationService.getAllReservations();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createReservation(@RequestBody ReservationsEntity reservation) {
        ApiResponse<ReservationsEntity> response = reservationService.createReservation(reservation);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable long id) {
        ApiResponse<ReservationsEntity> response = reservationService.getReservationById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateReservation(@PathVariable long id, @RequestBody ReservationsEntity updatedReservation) {
        ApiResponse<ReservationsEntity> response = reservationService.updateReservation(id, updatedReservation);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReservation(@PathVariable long id) {
        ApiResponse<String> response = reservationService.deleteReservation(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
