package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.DishesEntity;
import com.res.main.service.DishService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public ResponseEntity<?> getAllDishes() {
        ApiResponse<List<DishesEntity>> response = dishService.getAllDishes();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDishById(@PathVariable long id) {
        ApiResponse<DishesEntity> response = dishService.getDishById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createDish(@Valid @RequestBody DishesEntity newDish) {
        ApiResponse<DishesEntity> response = dishService.createDish(newDish);
        if (!response.isSuccess()) {
            return ResponseEntity.status(400).body(response); // Status 400 Bad Request
        }
        return ResponseEntity.status(201).body(response); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDish(@PathVariable long id, @Valid @RequestBody DishesEntity updatedDish) {
        ApiResponse<DishesEntity> response = dishService.updateDish(id, updatedDish);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDish(@PathVariable long id) {
        ApiResponse<String> response = dishService.deleteDish(id);
        return ResponseEntity.ok(response);
    }
}
