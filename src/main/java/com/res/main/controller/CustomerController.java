package com.res.main.controller;

import com.res.main.dto.LoginRequest;
import com.res.main.dto.LoginResponse;
import com.res.main.model.ApiResponse;
import com.res.main.model.CustomersEntity;
import com.res.main.service.CustomerService;
import com.res.main.service.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginService loginService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        ApiResponse<List<CustomersEntity>> response = customerService.getAllCustomers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomerById(@PathVariable Long id) {
        ApiResponse<CustomersEntity> response = customerService.findById(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomersEntity newCustomer) {
        ApiResponse<CustomersEntity> response = customerService.createCustomer(newCustomer);
        if (response.isSuccess()) {
            return ResponseEntity.status(400).body(response);
        }
        return ResponseEntity.status(201).body(response); // Status 201 Created
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@Valid @PathVariable Long id, @RequestBody CustomersEntity updatedCustomer) {
        ApiResponse<CustomersEntity> response = customerService.updateCustomer(id, updatedCustomer);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        ApiResponse<String> response = customerService.deleteCustomer(id);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    //    LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        ApiResponse<LoginResponse> response = loginService.loginCustomer(request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login-no-token")
    public ResponseEntity<?> loginNoToken(@RequestBody LoginRequest request) {
        ApiResponse<CustomersEntity> response = loginService.loginCustomerNoToken(request);
        if (!response.isSuccess()) {
            return ResponseEntity.status(404).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
