package com.res.main.service;

import com.res.main.dto.LoginRequest;
import com.res.main.dto.LoginResponse;
import com.res.main.model.ApiResponse;
import com.res.main.model.CustomersEntity;
import com.res.main.model.EmployeesEntity;
import com.res.main.repository.CustomerRepository;
import com.res.main.repository.EmployeeRepository;
import com.res.main.util.JwtUtil;
import com.res.main.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    EmployeeRepository employeeRepository;
    // LOGIN
    public ApiResponse<LoginResponse> loginCustomer(LoginRequest request) {
        Optional<CustomersEntity> customer = customerRepository.findByEmail(request.getEmail());
        if (customer.isPresent()) {
            if (PasswordUtil.checkPassword(request.getPassword(), customer.get().getPassword())) {
                String token = JwtUtil.generateToken(customer.get().getId(), Optional.empty()); // Không có vai trò cho khách hàng
                return new ApiResponse<>(true, "Login successful", new LoginResponse(token));
            }
        }
        return new ApiResponse<>(false, "Invalid email or password", new LoginResponse(null));
    }

    public ApiResponse<LoginResponse> loginEmployee(LoginRequest request) {
        Optional<EmployeesEntity> employee = employeeRepository.findByEmail(request.getEmail());
        if (employee.isPresent()) {
            if (PasswordUtil.checkPassword(request.getPassword(), employee.get().getPassword())) {
                String token = JwtUtil.generateToken(employee.get().getId(), Optional.of(employee.get().getRole())); // Có vai trò cho nhân viên
                return new ApiResponse<>(true, "Login successful", new LoginResponse(token));
            }
        }
        return new ApiResponse<>(false, "Invalid email or password", new LoginResponse(null));
    }

}
