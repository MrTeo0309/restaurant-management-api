package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CategoriesEntity;
import com.res.main.model.EmployeesEntity;
import com.res.main.repository.CategoryRepository;
import com.res.main.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public ApiResponse<List<EmployeesEntity>> getAllEmployee() {
        List<EmployeesEntity> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            return new ApiResponse<>("success", employees, "No employees found");
        }
        return new ApiResponse<>("success", employees, "Employees retrieved successfully");

    }

    public ApiResponse<EmployeesEntity> createEmployee(EmployeesEntity employee) {
        EmployeesEntity employees = employeeRepository.save(employee);
        return new ApiResponse<>("success", employees, "Employees created successfully");
    }

    public ApiResponse<String> deleteEmployee(long id) {
        if (employeeRepository.existsById(id)){
            employeeRepository.deleteById(id);
            return new ApiResponse<>("success", "Employee deleted", "Employees deleted successfully");

        }
        return new ApiResponse<>("error", null, "Not found");

    }

    public ApiResponse<EmployeesEntity> updateEmployee(EmployeesEntity updateEmployee, long id) {

        if (employeeRepository.existsById(id)) {
            updateEmployee.setId(id);
            employeeRepository.save(updateEmployee);
            return new ApiResponse<>("success", updateEmployee, "Employees update successfully");
        }
        return new ApiResponse<>("error", null, "Employee not found");


    }

    public ApiResponse<EmployeesEntity> findById(long id) {
        Optional<EmployeesEntity> employee = employeeRepository.findById(id);
        if (employee.isPresent()){
            return new ApiResponse<>("success", employee.get(), "Employees update ");
        }
        return new ApiResponse<>("error", null, "Employee not found");
    }
}
