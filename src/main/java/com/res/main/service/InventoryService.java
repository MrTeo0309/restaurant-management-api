package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.InventoryEntity;
import com.res.main.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public ApiResponse<List<InventoryEntity>> getAllInventories() {
        List<InventoryEntity> inventory = inventoryRepository.findAll();
        if (inventory.isEmpty()) {
            return new ApiResponse<>(false, "No inventory found", inventory);
        }
        return new ApiResponse<>(true, "inventory retrieved successfully", inventory);
    }

    public ApiResponse<InventoryEntity> createInventory(InventoryEntity inventory) {
        InventoryEntity savedInventory = inventoryRepository.save(inventory);
        return new ApiResponse<>(true, "inventory created successfully", savedInventory);
    }

    public ApiResponse<InventoryEntity> getInventoryById(long id) {
        Optional<InventoryEntity> inventory = inventoryRepository.findById(id);
        if (inventory.isPresent()) {
            return new ApiResponse<>(true, "inventory retrieved successfully", inventory.get());
        }
        return new ApiResponse<>(false, "inventory not found", null);
    }

    public ApiResponse<InventoryEntity> updateInventory(long id, InventoryEntity updatedInventory) {
        if (inventoryRepository.existsById(id)) {
            updatedInventory.setId(id);
            InventoryEntity savedInventory = inventoryRepository.save(updatedInventory);
            return new ApiResponse<>(true, "inventory updated successfully", savedInventory);
        }
        return new ApiResponse<>(false, "inventory not found", null);
    }

    public ApiResponse<String> deleteInventory(long id) {
        if (inventoryRepository.existsById(id)) {
            inventoryRepository.deleteById(id);
            return new ApiResponse<>(true, "inventory deleted successfully", "inventory deleted");
        }
        return new ApiResponse<>(false, "inventory not found", null);
    }
}
