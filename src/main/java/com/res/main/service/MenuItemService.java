package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.MenuItemsEntity;
import com.res.main.repository.MenuItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;

    public ApiResponse<List<MenuItemsEntity>> getAllMenuItems() {
        List<MenuItemsEntity> menuItems = menuItemRepository.findAll();
        if (menuItems.isEmpty()) {
            return new ApiResponse<>(false, "No menu items found", menuItems);
        }
        return new ApiResponse<>(true, "Menu items retrieved successfully", menuItems);
    }

    public ApiResponse<MenuItemsEntity> createMenuItem(MenuItemsEntity menuItem) {
        MenuItemsEntity savedMenuItem = menuItemRepository.save(menuItem);
        return new ApiResponse<>(true, "Menu item created successfully", savedMenuItem);
    }

    public ApiResponse<MenuItemsEntity> getMenuItemById(long id) {
        Optional<MenuItemsEntity> menuItem = menuItemRepository.findById(id);
        if (menuItem.isPresent()) {
            return new ApiResponse<>(true, "Menu item retrieved successfully", menuItem.get());
        }
        return new ApiResponse<>(false, "Menu item not found", null);
    }

    public ApiResponse<MenuItemsEntity> updateMenuItem(long id, MenuItemsEntity updatedMenuItem) {
        if (menuItemRepository.existsById(id)) {
            updatedMenuItem.setId(id);
            MenuItemsEntity savedMenuItem = menuItemRepository.save(updatedMenuItem);
            return new ApiResponse<>(true, "Menu item updated successfully", savedMenuItem);
        }
        return new ApiResponse<>(false, "Menu item not found", null);
    }

    public ApiResponse<String> deleteMenuItem(long id) {
        if (menuItemRepository.existsById(id)) {
            menuItemRepository.deleteById(id);
            return new ApiResponse<>(true, "Menu item deleted successfully", "Menu item deleted");
        }
        return new ApiResponse<>(false, "Menu item not found", null);
    }
}
