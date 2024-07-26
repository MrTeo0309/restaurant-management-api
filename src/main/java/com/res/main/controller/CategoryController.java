package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.CategoriesEntity;
import com.res.main.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getAllCategories() {
        ApiResponse<List<CategoriesEntity>> response = categoryService.getAllCategories();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable long id) {
        ApiResponse<CategoriesEntity> response = categoryService.getCategoryById(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createCategory(@Valid @RequestBody CategoriesEntity newCategory) {
        ApiResponse<CategoriesEntity> response = categoryService.createCategory(newCategory);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.status(201).body(response); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @PathVariable long id, @RequestBody CategoriesEntity updatedCategory) {
        ApiResponse<CategoriesEntity> response = categoryService.updateCategory(id, updatedCategory);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable long id) {
        ApiResponse<String> response = categoryService.deleteCategory(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
