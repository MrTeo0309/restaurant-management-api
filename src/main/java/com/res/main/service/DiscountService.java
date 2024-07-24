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
    private DiscountRepository discountRepository;

    public ApiResponse<List<DiscountsEntity>> getAllDiscounts() {
        List<DiscountsEntity> discounts = discountRepository.findAll();
        if (discounts.isEmpty()) {
            return new ApiResponse<>(false, "No discounts found", discounts);
        }
        return new ApiResponse<>(true, "Discounts retrieved successfully", discounts);
    }

    public ApiResponse<DiscountsEntity> getDiscountById(long id) {
        Optional<DiscountsEntity> discount = discountRepository.findById(id);
        if (discount.isPresent()) {
            return new ApiResponse<>(true, "Discount retrieved successfully", discount.get());
        } else {
            return new ApiResponse<>(false, "Discount not found", null);
        }
    }

    public ApiResponse<DiscountsEntity> createDiscount(DiscountsEntity newDiscount) {
        try {
            DiscountsEntity savedDiscount = discountRepository.save(newDiscount);
            return new ApiResponse<>(true, "Discount created successfully", savedDiscount);
        } catch (DataIntegrityViolationException e) {
            return new ApiResponse<>(false, "Discount code already exists", null);
        }
    }

    public ApiResponse<DiscountsEntity> updateDiscount(long id, DiscountsEntity updatedDiscount) {
        if (discountRepository.existsById(id)) {
            updatedDiscount.setId(id);
            DiscountsEntity savedDiscount = discountRepository.save(updatedDiscount);
            return new ApiResponse<>(true, "Discount updated successfully", savedDiscount);
        } else {
            return new ApiResponse<>(false, "Discount not found", null);
        }
    }

    public ApiResponse<String> deleteDiscount(long id) {
        if (discountRepository.existsById(id)) {
            discountRepository.deleteById(id);
            return new ApiResponse<>(true, "Discount deleted successfully", "Discount deleted");
        } else {
            return new ApiResponse<>(false, "Discount not found", null);
        }
    }
}
