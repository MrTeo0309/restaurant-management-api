package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.EmployeesEntity;
import com.res.main.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping
    public ResponseEntity<?> getAllEmployee() {
        ApiResponse<List<EmployeesEntity>> employees = employeeService.getAllEmployee();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createEmployee(@Valid @RequestBody EmployeesEntity newEmployee) {
        ApiResponse<EmployeesEntity> employee = employeeService.createEmployee(newEmployee);
        if (employee.getStatus().equals("error")) {
            return ResponseEntity.status(400).body(employee);
        }
        return ResponseEntity.status(201).body(employee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable long id) {
        ApiResponse<String> response = employeeService.deleteEmployee(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable long id, @Valid
    @RequestBody EmployeesEntity updateEmployee) {
        ApiResponse<EmployeesEntity> employee = employeeService.updateEmployee(updateEmployee, id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findEmployee(@PathVariable long id) {
        ApiResponse<EmployeesEntity> employee = employeeService.findById(id);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }
}
