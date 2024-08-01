package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CartItemsEntity;
import com.res.main.repository.CartItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartItemService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public ApiResponse<List<CartItemsEntity>> getAllCartItems() {
        List<CartItemsEntity> cartItems = cartItemRepository.findAll();
        if (cartItems.isEmpty()) {
            return new ApiResponse<>(false, "No cart items found", cartItems);
        }
        return new ApiResponse<>(true, "Cart items retrieved successfully", cartItems);
    }

    public ApiResponse<CartItemsEntity> createCartItem(CartItemsEntity cartItem) {
        CartItemsEntity savedCartItem = cartItemRepository.save(cartItem);
        return new ApiResponse<>(true, "Cart item created successfully", savedCartItem);
    }

    public ApiResponse<CartItemsEntity> getCartItemById(long id) {
        Optional<CartItemsEntity> cartItem = cartItemRepository.findById(id);
        if (cartItem.isPresent()) {
            return new ApiResponse<>(true, "Cart item retrieved successfully", cartItem.get());
        }
        return new ApiResponse<>(false, "Cart item not found", null);
    }

    public ApiResponse<CartItemsEntity> updateCartItem(long id, CartItemsEntity updatedCartItem) {
        if (cartItemRepository.existsById(id)) {
            updatedCartItem.setId(id);
            CartItemsEntity savedCartItem = cartItemRepository.save(updatedCartItem);
            return new ApiResponse<>(true, "Cart item updated successfully", savedCartItem);
        }
        return new ApiResponse<>(false, "Cart item not found", null);
    }

    public ApiResponse<String> deleteCartItem(long id) {
        if (cartItemRepository.existsById(id)) {
            cartItemRepository.deleteById(id);
            return new ApiResponse<>(true, "Cart item deleted successfully", "Cart item deleted");
        }
        return new ApiResponse<>(false, "Cart item not found", null);
    }
}
