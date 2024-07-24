package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CategoriesEntity;
import com.res.main.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public ApiResponse<List<CategoriesEntity>> getAllCategories() {
        List<CategoriesEntity> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new ApiResponse<>("success", categories, "No categories found");
        }
        return new ApiResponse<>("success", categories, "Categories retrieved successfully");
    }

    public ApiResponse<CategoriesEntity> getCategoryById(long id) {
        Optional<CategoriesEntity> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return new ApiResponse<>("success", category.get(), "Category retrieved successfully");
        } else {
            return new ApiResponse<>("error", null, "Category not found");
        }
    }

    public ApiResponse<CategoriesEntity> createCategory(CategoriesEntity newCategory) {
        CategoriesEntity savedCategory = categoryRepository.save(newCategory);
        return new ApiResponse<>("success", savedCategory, "Category created successfully");
    }

    public ApiResponse<CategoriesEntity> updateCategory(long id, CategoriesEntity updatedCategory) {
        if (categoryRepository.existsById(id)) {
            updatedCategory.setId(id);
            CategoriesEntity savedCategory = categoryRepository.save(updatedCategory);
            return new ApiResponse<>("success", savedCategory, "Category updated successfully");
        } else {
            return new ApiResponse<>("error", null, "Category not found");
        }
    }

    public ApiResponse<String> deleteCategory(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new ApiResponse<>("success", "Category deleted", "Category deleted successfully");
        } else {
            return new ApiResponse<>("error", null, "Category not found");
        }
    }
}
