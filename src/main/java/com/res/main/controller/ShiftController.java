package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.ShiftsEntity;
import com.res.main.service.ShiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shifts")
public class ShiftController {

    @Autowired
    private ShiftService shiftService;

    @GetMapping
    public ResponseEntity<?> getAllShifts() {
        ApiResponse<List<ShiftsEntity>> response = shiftService.getAllShifts();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createShift(@RequestBody ShiftsEntity shift) {
        ApiResponse<ShiftsEntity> response = shiftService.createShift(shift);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getShiftById(@PathVariable long id) {
        ApiResponse<ShiftsEntity> response = shiftService.getShiftById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateShift(@PathVariable long id, @RequestBody ShiftsEntity updatedShift) {
        ApiResponse<ShiftsEntity> response = shiftService.updateShift(id, updatedShift);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteShift(@PathVariable long id) {
        ApiResponse<String> response = shiftService.deleteShift(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
