package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.CartEntity;
import com.res.main.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public ApiResponse<List<CartEntity>> getAllCarts() {
        List<CartEntity> carts = cartRepository.findAll();
        if (carts.isEmpty()) {
            return new ApiResponse<>(false, "No carts found", carts);
        }
        return new ApiResponse<>(true, "Carts retrieved successfully", carts);
    }

    public ApiResponse<CartEntity> createCart(CartEntity cart) {
        CartEntity savedCart = cartRepository.save(cart);
        return new ApiResponse<>(true, "Cart created successfully", savedCart);
    }

    public ApiResponse<CartEntity> getCartById(long id) {
        Optional<CartEntity> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            return new ApiResponse<>(true, "Cart retrieved successfully", cart.get());
        }
        return new ApiResponse<>(false, "Cart not found", null);
    }

    public ApiResponse<CartEntity> updateCart(long id, CartEntity updatedCart) {
        if (cartRepository.existsById(id)) {
            updatedCart.setId(id);
            CartEntity savedCart = cartRepository.save(updatedCart);
            return new ApiResponse<>(true, "Cart updated successfully", savedCart);
        }
        return new ApiResponse<>(false, "Cart not found", null);
    }

    public ApiResponse<String> deleteCart(long id) {
        if (cartRepository.existsById(id)) {
            cartRepository.deleteById(id);
            return new ApiResponse<>(true, "Cart deleted successfully", "Cart deleted");
        }
        return new ApiResponse<>(false, "Cart not found", null);
    }
}
