package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CategoriesEntity;
import com.res.main.model.SuppliersEntity;
import com.res.main.repository.CategoryRepository;
import com.res.main.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    public ApiResponse<List<SuppliersEntity>> getAllSuppliers() {
        List<SuppliersEntity> suppliers = supplierRepository.findAll();
        if (suppliers.isEmpty()) {
            return new ApiResponse<>("success", suppliers, "No supplier found");
        }
        return new ApiResponse<>("success", suppliers, "Supplier retrieved successfully");
    }

    public ApiResponse<SuppliersEntity> createSupplier(SuppliersEntity supplier) {
        SuppliersEntity saveSupplier = supplierRepository.save(supplier);
        return new ApiResponse<>("success", saveSupplier, "Supplier created successfully");
    }

    public ApiResponse<SuppliersEntity> getSupplierId(long id) {
        Optional<SuppliersEntity> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return new ApiResponse<>("success", supplier.get(), "Supplier retrieved successfully");
        }
        return new ApiResponse<>("error", null, "Supplier not found");
    }

    public ApiResponse<SuppliersEntity> updateSupplier(long Id, SuppliersEntity updatedSupplier) {
        if (supplierRepository.existsById(Id)){
            updatedSupplier.setId(Id);
            SuppliersEntity savedSupplier = supplierRepository.save(updatedSupplier);
            return new ApiResponse<>("success", savedSupplier, "Supplier updated successfully");
        }
        return new ApiResponse<>("error", null, "Supplier not found");
    }

    public ApiResponse<String> deleteSupplier(long Id) {
        if (supplierRepository.existsById(Id)){
            supplierRepository.deleteById(Id);
            return new ApiResponse<>("success", "Supplier delete", "Supplier deleted successfully");
        } else {
            return new ApiResponse<>("error", null, "Supplier not found");
        }
    }
}
