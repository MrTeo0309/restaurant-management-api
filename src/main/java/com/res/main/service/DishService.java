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
    private DishRepository dishRepository;

    public ApiResponse<List<DishesEntity>> getAllDishes() {
        List<DishesEntity> dishes = dishRepository.findAll();
        if (dishes.isEmpty()) {
            return new ApiResponse<>(false, "No dishes found", dishes);
        }
        return new ApiResponse<>(true, "Dishes retrieved successfully", dishes);
    }

    public ApiResponse<DishesEntity> getDishById(long id) {
        Optional<DishesEntity> dish = dishRepository.findById(id);
        if (dish.isPresent()) {
            return new ApiResponse<>(true, "Dish retrieved successfully", dish.get());
        } else {
            return new ApiResponse<>(false, "Dish not found", null);
        }
    }

    public ApiResponse<DishesEntity> createDish(DishesEntity newDish) {
        DishesEntity savedDish = dishRepository.save(newDish);
        return new ApiResponse<>(true, "Dish created successfully", savedDish);
    }

    public ApiResponse<DishesEntity> updateDish(long id, DishesEntity updatedDish) {
        if (dishRepository.existsById(id)) {
            updatedDish.setId(id);
            DishesEntity savedDish = dishRepository.save(updatedDish);
            return new ApiResponse<>(true, "Dish updated successfully", savedDish);
        } else {
            return new ApiResponse<>(false, "Dish not found", null);
        }
    }

    public ApiResponse<String> deleteDish(long id) {
        if (dishRepository.existsById(id)) {
            dishRepository.deleteById(id);
            return new ApiResponse<>(true, "Dish deleted successfully", "Dish deleted");
        } else {
            return new ApiResponse<>(false, "Dish not found", null);
        }
    }
}
