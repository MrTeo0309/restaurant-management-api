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
        ApiResponse<List<SuppliersEntity>> suppliers = supplierService.getAllSuppliers();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SuppliersEntity newSupplier) {
        ApiResponse<SuppliersEntity> supplier = supplierService.createSupplier(newSupplier);
        if (!supplier.isSuccess()) {
            return ResponseEntity.status(400).body(supplier);
        }
        return ResponseEntity.status(201).body(supplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplier(@PathVariable long id) {
        ApiResponse<SuppliersEntity> supplier = supplierService.getSupplierById(id);
        if (supplier == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(supplier, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable long id, @Valid
    @RequestBody SuppliersEntity updatedSupplier) {
        ApiResponse<SuppliersEntity> supplier = supplierService.updateSupplier(id, updatedSupplier);
        return ResponseEntity.ok(supplier);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable long id) {
        ApiResponse<String> supplier = supplierService.deleteSupplier(id);
        return ResponseEntity.ok(supplier);
    }
}
