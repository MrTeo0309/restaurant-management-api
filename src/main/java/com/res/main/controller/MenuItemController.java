package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.MenuItemsEntity;
import com.res.main.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/menu-items")
public class MenuItemController {

    @Autowired
    private MenuItemService menuItemService;

    @GetMapping
    public ResponseEntity<?> getAllMenuItems() {
        ApiResponse<List<MenuItemsEntity>> response = menuItemService.getAllMenuItems();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createMenuItem(@RequestBody MenuItemsEntity menuItem) {
        ApiResponse<MenuItemsEntity> response = menuItemService.createMenuItem(menuItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getMenuItemById(@PathVariable long id) {
        ApiResponse<MenuItemsEntity> response = menuItemService.getMenuItemById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMenuItem(@PathVariable long id, @RequestBody MenuItemsEntity updatedMenuItem) {
        ApiResponse<MenuItemsEntity> response = menuItemService.updateMenuItem(id, updatedMenuItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteMenuItem(@PathVariable long id) {
        ApiResponse<String> response = menuItemService.deleteMenuItem(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
