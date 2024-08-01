package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.SuppliersEntity;
import com.res.main.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/suppliers")
@CrossOrigin(origins = "*")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        ApiResponse<List<SuppliersEntity>> response = supplierService.getAllSuppliers();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SuppliersEntity newSupplier) {
        ApiResponse<SuppliersEntity> response = supplierService.createSupplier(newSupplier);
        if (!response.isSuccess()) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable long id) {
        ApiResponse<SuppliersEntity> response = supplierService.getSupplierById(id);
        if (response == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable long id, @Valid
    @RequestBody SuppliersEntity updatedSupplier) {
        ApiResponse<SuppliersEntity> response = supplierService.updateSupplier(id, updatedSupplier);
        if (!response.isSuccess()) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable long id) {
        ApiResponse<String> response = supplierService.deleteSupplier(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
