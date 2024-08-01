package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.RateEntity;
import com.res.main.repository.RateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RateService {

    @Autowired
    private RateRepository rateRepository;

    public ApiResponse<List<RateEntity>> getAllRates() {
        List<RateEntity> rates = rateRepository.findAll();
        if (rates.isEmpty()) {
            return new ApiResponse<>(false, "No rates found", rates);
        }
        return new ApiResponse<>(true, "Rates retrieved successfully", rates);
    }

    public ApiResponse<RateEntity> createRate(RateEntity rate) {
        RateEntity savedRate = rateRepository.save(rate);
        return new ApiResponse<>(true, "Rate created successfully", savedRate);
    }

    public ApiResponse<RateEntity> getRateById(long id) {
        Optional<RateEntity> rate = rateRepository.findById(id);
        if (rate.isPresent()) {
            return new ApiResponse<>(true, "Rate retrieved successfully", rate.get());
        }
        return new ApiResponse<>(false, "Rate not found", null);
    }

    public ApiResponse<RateEntity> updateRate(long id, RateEntity updatedRate) {
        if (rateRepository.existsById(id)) {
            updatedRate.setId(id);
            RateEntity savedRate = rateRepository.save(updatedRate);
            return new ApiResponse<>(true, "Rate updated successfully", savedRate);
        }
        return new ApiResponse<>(false, "Rate not found", null);
    }

    public ApiResponse<String> deleteRate(long id) {
        if (rateRepository.existsById(id)) {
            rateRepository.deleteById(id);
            return new ApiResponse<>(true, "Rate deleted successfully", "Rate deleted");
        }
        return new ApiResponse<>(false, "Rate not found", null);
    }
}
