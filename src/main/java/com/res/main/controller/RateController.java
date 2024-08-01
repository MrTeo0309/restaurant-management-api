package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.RateEntity;
import com.res.main.service.RateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rates")
public class RateController {

    @Autowired
    private RateService rateService;

    @GetMapping
    public ResponseEntity<?> getAllRates() {
        ApiResponse<List<RateEntity>> response = rateService.getAllRates();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createRate(@RequestBody RateEntity rate) {
        ApiResponse<RateEntity> response = rateService.createRate(rate);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRateById(@PathVariable long id) {
        ApiResponse<RateEntity> response = rateService.getRateById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateRate(@PathVariable long id, @RequestBody RateEntity updatedRate) {
        ApiResponse<RateEntity> response = rateService.updateRate(id, updatedRate);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRate(@PathVariable long id) {
        ApiResponse<String> response = rateService.deleteRate(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
