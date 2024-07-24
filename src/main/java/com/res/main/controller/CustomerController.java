package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.CustomersEntity;
import com.res.main.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        ApiResponse<List<CustomersEntity>> response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        ApiResponse<CustomersEntity> response = customerService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomersEntity newCustomer) {
        ApiResponse<CustomersEntity> response = customerService.createCustomer(newCustomer);
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.status(201).body(response); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @PathVariable Long id, @RequestBody CustomersEntity updatedCustomer) {
        ApiResponse<CustomersEntity> response = customerService.updateCustomer(id, updatedCustomer);
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        ApiResponse<String> response = customerService.deleteCustomer(id);
        if ("error".equals(response.getStatus())) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
