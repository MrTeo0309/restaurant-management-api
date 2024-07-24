package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.DiscountsEntity;
import com.res.main.repository.DiscountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public ApiResponse<List<DiscountsEntity>> getAllDiscounts() {
        List<DiscountsEntity> discounts = discountRepository.findAll();
        if (discounts.isEmpty()) {
            return new ApiResponse<>("success", discounts, "No discounts found");
        }
        return new ApiResponse<>("success", discounts, "Discounts retrieved successfully");
    }

    public ApiResponse<DiscountsEntity> getDiscountById(long id) {
        Optional<DiscountsEntity> discount = discountRepository.findById(id);
        if (discount.isPresent()) {
            return new ApiResponse<>("success", discount.get(), "Discount retrieved successfully");
        } else {
            return new ApiResponse<>("error", null, "Discount not found");
        }
    }

    public ApiResponse<DiscountsEntity> createDiscount(DiscountsEntity newDiscount) {
        try {
            DiscountsEntity savedDiscount = discountRepository.save(newDiscount);
            return new ApiResponse<>("success", savedDiscount, "Discount created successfully");
        } catch (DataIntegrityViolationException e) {
            return new ApiResponse<>("error", null, "Discount code already exists");
        }
    }

    public ApiResponse<DiscountsEntity> updateDiscount(long id, DiscountsEntity updatedDiscount) {
        if (discountRepository.existsById(id)) {
            updatedDiscount.setId(id);
            DiscountsEntity savedDiscount = discountRepository.save(updatedDiscount);
            return new ApiResponse<>("success", savedDiscount, "Discount updated successfully");
        } else {
            return new ApiResponse<>("error", null, "Discount not found");
        }
    }

    public ApiResponse<String> deleteDiscount(long id) {
        if (discountRepository.existsById(id)) {
            discountRepository.deleteById(id);
            return new ApiResponse<>("success", "Discount deleted", "Discount deleted successfully");
        } else {
            return new ApiResponse<>("error", null, "Discount not found");
        }
    }
}
