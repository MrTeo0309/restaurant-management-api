package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.DishesEntity;
import com.res.main.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    DishRepository dishRepository;

    public ApiResponse<List<DishesEntity>> getAllDishes() {
        List<DishesEntity> dishes = dishRepository.findAll();
        if (dishes.isEmpty()) {
            return new ApiResponse<>("success", dishes, "No dishes found");
        }
        return new ApiResponse<>("success", dishes, "Dishes retrieved successfully");
    }

    public ApiResponse<DishesEntity> getDishById(long id) {
        Optional<DishesEntity> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            return new ApiResponse<>("success", dish.get(), "Dish retrieved successfully");
        } else {
            return new ApiResponse<>("error", null, "Dish not found");
        }
    }

    public ApiResponse<DishesEntity> createDish(DishesEntity newDish) {
        DishesEntity savedDish = dishRepository.save(newDish);
        return new ApiResponse<>("success", savedDish, "Dish created successfully");
    }

    public ApiResponse<DishesEntity> updateDish(long id, DishesEntity updatedDish) {
        if (dishRepository.existsById(id)) {
            updatedDish.setId(id);
            DishesEntity savedDish = dishRepository.save(updatedDish);
            return new ApiResponse<>("success", savedDish, "Dish updated successfully");
        } else {
            return new ApiResponse<>("error", null, "Dish not found");
        }
    }

    public ApiResponse<String> deleteDish(long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return new ApiResponse<>("success", "Dish deleted", "Dish deleted successfully");
        } else {
            return new ApiResponse<>("error", null, "Dish not found");
        }
    }
}
