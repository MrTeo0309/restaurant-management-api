package com.res.main.service;

import com.res.main.dto.LoginRequest;
import com.res.main.dto.LoginResponse;
import com.res.main.model.ApiResponse;
import com.res.main.model.CustomersEntity;
import com.res.main.model.EmployeesEntity;
import com.res.main.repository.CustomerRepository;
import com.res.main.util.JwtUtil;
import com.res.main.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public ApiResponse<List<CustomersEntity>> getAllCustomers() {
        List<CustomersEntity> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            return new ApiResponse<>(false, "No customers found", customers);
        }
        return new ApiResponse<>(true, "Customers retrieved successfully", customers);
    }

    public ApiResponse<CustomersEntity> findById(Long id) {
        Optional<CustomersEntity> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ApiResponse<>(true, "Customer retrieved successfully", customer.get());
        } else {
            return new ApiResponse<>(false, "Customer not found", null);
        }
    }

    public ApiResponse<CustomersEntity> createCustomer(CustomersEntity customer) {
        Optional<CustomersEntity> customerExists = customerRepository.findByEmail(customer.getEmail());
        if (customerExists.isPresent()) {
            return new ApiResponse<>(false, "Email already in use", null);
        }
        customer.setPassword(PasswordUtil.hashPassword(customer.getPassword()));
        CustomersEntity savedCustomer = customerRepository.save(customer);
        return new ApiResponse<>(true, "Customer created successfully", savedCustomer);
    }

    public ApiResponse<CustomersEntity> updateCustomer(long customerId, CustomersEntity updatedCustomer) {
        if (customerRepository.existsById(customerId)) {
            updatedCustomer.setId(customerId);
            CustomersEntity savedCustomer = customerRepository.save(updatedCustomer);
            return new ApiResponse<>(true, "Customer updated successfully", savedCustomer);
        } else {
            return new ApiResponse<>(false, "Customer not found", null);
        }
    }

    public ApiResponse<String> deleteCustomer(long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return new ApiResponse<>(true, "Customer deleted successfully", "Customer deleted");
        } else {
            return new ApiResponse<>(false, "Customer not found", null);
        }
    }

    public ApiResponse<CustomersEntity> findByEmail(String email) {
        Optional<CustomersEntity> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return new ApiResponse<>(true, "Customer found", customer.get());
        } else {
            return new ApiResponse<>(false, "Customer not found", null);
        }
    }
}
