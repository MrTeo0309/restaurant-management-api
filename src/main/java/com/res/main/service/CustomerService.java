package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CustomersEntity;
import com.res.main.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
            return new ApiResponse<>("success", customers, "No customers found");
        }
        return new ApiResponse<>("success", customers, "Customers retrieved successfully");
    }

    public ApiResponse<CustomersEntity> findById(Long id) {
        Optional<CustomersEntity> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            return new ApiResponse<>("success", customer.get(), "Customer retrieved successfully");
        } else {
            return new ApiResponse<>("error", null, "Customer not found");
        }
    }

    public ApiResponse<CustomersEntity> createCustomer(CustomersEntity customer) {
        Optional<CustomersEntity> customerExists = customerRepository.findByEmail(customer.getEmail());
        if (customerExists.isPresent()) {
            return new ApiResponse<>("error", null, "Email already in use");
        }
        CustomersEntity savedCustomer = customerRepository.save(customer);
        return new ApiResponse<>("success", savedCustomer, "Customer created successfully");
    }

    public ApiResponse<CustomersEntity> updateCustomer(long customerId, CustomersEntity updatedCustomer) {
        if (customerRepository.existsById(customerId)) {
            updatedCustomer.setId(customerId);
            CustomersEntity savedCustomer = customerRepository.save(updatedCustomer);
            return new ApiResponse<>("success", savedCustomer, "Customer updated successfully");
        } else {
            return new ApiResponse<>("error", null, "Customer not found");
        }
    }

    public ApiResponse<String> deleteCustomer(long customerId) {
        if (customerRepository.existsById(customerId)) {
            customerRepository.deleteById(customerId);
            return new ApiResponse<>("success", "Customer deleted", "Customer deleted successfully");
        } else {
            return new ApiResponse<>("error", null, "Customer not found");
        }
    }

    public ApiResponse<CustomersEntity> findByEmail(String email) {
        Optional<CustomersEntity> customer = customerRepository.findByEmail(email);
        if (customer.isPresent()) {
            return new ApiResponse<>("success", customer.get(), "Customer found");
        } else {
            return new ApiResponse<>("error", null, "Customer not found");
        }
    }
}
