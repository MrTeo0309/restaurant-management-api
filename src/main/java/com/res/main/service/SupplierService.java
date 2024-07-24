package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.SuppliersEntity;
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
            return new ApiResponse<>(false, "No suppliers found", suppliers);
        }
        return new ApiResponse<>(true, "Suppliers retrieved successfully", suppliers);
    }

    public ApiResponse<SuppliersEntity> createSupplier(SuppliersEntity supplier) {
        SuppliersEntity savedSupplier = supplierRepository.save(supplier);
        return new ApiResponse<>(true, "Supplier created successfully", savedSupplier);
    }

    public ApiResponse<SuppliersEntity> getSupplierById(long id) {
        Optional<SuppliersEntity> supplier = supplierRepository.findById(id);
        if (supplier.isPresent()) {
            return new ApiResponse<>(true, "Supplier retrieved successfully", supplier.get());
        }
        return new ApiResponse<>(false, "Supplier not found", null);
    }

    public ApiResponse<SuppliersEntity> updateSupplier(long id, SuppliersEntity updatedSupplier) {
        if (supplierRepository.existsById(id)) {
            updatedSupplier.setId(id);
            SuppliersEntity savedSupplier = supplierRepository.save(updatedSupplier);
            return new ApiResponse<>(true, "Supplier updated successfully", savedSupplier);
        }
        return new ApiResponse<>(false, "Supplier not found", null);
    }

    public ApiResponse<String> deleteSupplier(long id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return new ApiResponse<>(true, "Supplier deleted successfully", "Supplier deleted");
        }
        return new ApiResponse<>(false, "Supplier not found", null);
    }
}
