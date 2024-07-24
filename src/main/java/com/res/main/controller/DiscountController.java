package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.DiscountsEntity;
import com.res.main.service.DiscountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discounts")
public class DiscountController {

    @Autowired
    private DiscountService discountService;

    @GetMapping
    public ResponseEntity<?> getAllDiscounts() {
        ApiResponse<List<DiscountsEntity>> response = discountService.getAllDiscounts();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDiscountById(@PathVariable long id) {
        ApiResponse<DiscountsEntity> response = discountService.getDiscountById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createDiscount(@Valid @RequestBody DiscountsEntity newDiscount) {
        ApiResponse<DiscountsEntity> response = discountService.createDiscount(newDiscount);
        if (response.getStatus().equals("error")) {
            return ResponseEntity.status(400).body(response); // Status 400 Bad Request
        }
        return ResponseEntity.status(201).body(response); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDiscount(@PathVariable long id, @Valid @RequestBody DiscountsEntity updatedDiscount) {
        ApiResponse<DiscountsEntity> response = discountService.updateDiscount(id, updatedDiscount);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDiscount(@PathVariable long id) {
        ApiResponse<String> response = discountService.deleteDiscount(id);
        return ResponseEntity.ok(response);
    }
}
