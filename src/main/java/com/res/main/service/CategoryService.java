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
    private CategoryRepository categoryRepository;

    public ApiResponse<List<CategoriesEntity>> getAllCategories() {
        List<CategoriesEntity> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            return new ApiResponse<>(false, "No categories found", categories);
        }
        return new ApiResponse<>(true, "Categories retrieved successfully", categories);
    }

    public ApiResponse<CategoriesEntity> getCategoryById(long id) {
        Optional<CategoriesEntity> category = categoryRepository.findById(id);
        if (category.isPresent()) {
            return new ApiResponse<>(true, "Category retrieved successfully", category.get());
        } else {
            return new ApiResponse<>(false, "Category not found", null);
        }
    }

    public ApiResponse<CategoriesEntity> createCategory(CategoriesEntity newCategory) {
        CategoriesEntity savedCategory = categoryRepository.save(newCategory);
        return new ApiResponse<>(true, "Category created successfully", savedCategory);
    }

    public ApiResponse<CategoriesEntity> updateCategory(long id, CategoriesEntity updatedCategory) {
        if (categoryRepository.existsById(id)) {
            updatedCategory.setId(id);
            CategoriesEntity savedCategory = categoryRepository.save(updatedCategory);
            return new ApiResponse<>(true, "Category updated successfully", savedCategory);
        } else {
            return new ApiResponse<>(false, "Category not found", null);
        }
    }

    public ApiResponse<String> deleteCategory(long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return new ApiResponse<>(true, "Category deleted successfully", "Category deleted");
        } else {
            return new ApiResponse<>(false, "Category not found", null);
        }
    }
}
