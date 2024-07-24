package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.EmployeesEntity;
import com.res.main.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public ApiResponse<List<EmployeesEntity>> getAllEmployees() {
        List<EmployeesEntity> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return new ApiResponse<>(false, "No employees found", employees);
        }
        return new ApiResponse<>(true, "Employees retrieved successfully", employees);
    }

    public ApiResponse<EmployeesEntity> createEmployee(EmployeesEntity employee) {
        EmployeesEntity savedEmployee = employeeRepository.save(employee);
        return new ApiResponse<>(true, "Employee created successfully", savedEmployee);
    }

    public ApiResponse<String> deleteEmployee(long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return new ApiResponse<>(true, "Employee deleted successfully", "Employee deleted");
        }
        return new ApiResponse<>(false, "Employee not found", null);
    }

    public ApiResponse<EmployeesEntity> updateEmployee(long id, EmployeesEntity updatedEmployee) {
        if (employeeRepository.existsById(id)) {
            updatedEmployee.setId(id);
            EmployeesEntity savedEmployee = employeeRepository.save(updatedEmployee);
            return new ApiResponse<>(true, "Employee updated successfully", savedEmployee);
        }
        return new ApiResponse<>(false, "Employee not found", null);
    }

    public ApiResponse<EmployeesEntity> findById(long id) {
        Optional<EmployeesEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return new ApiResponse<>(true, "Employee retrieved successfully", employee.get());
        }
        return new ApiResponse<>(false, "Employee not found", null);
    }
}
