package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.InventoryEntity;
import com.res.main.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?> getAllInventories() {
        ApiResponse<List<InventoryEntity>> response = inventoryService.getAllInventories();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createInventory(@RequestBody InventoryEntity inventory) {
        ApiResponse<InventoryEntity> response = inventoryService.createInventory(inventory);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventoryById(@PathVariable long id) {
        ApiResponse<InventoryEntity> response = inventoryService.getInventoryById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@PathVariable long id, @RequestBody InventoryEntity updatedInventory) {
        ApiResponse<InventoryEntity> response = inventoryService.updateInventory(id, updatedInventory);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@PathVariable long id) {
        ApiResponse<String> response = inventoryService.deleteInventory(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
