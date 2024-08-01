package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.CartItemsEntity;
import com.res.main.service.CartItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart-items")
public class CartItemController {

    @Autowired
    private CartItemService cartItemService;

    @GetMapping
    public ResponseEntity<?> getAllCartItems() {
        ApiResponse<List<CartItemsEntity>> response = cartItemService.getAllCartItems();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCartItem(@RequestBody CartItemsEntity cartItem) {
        ApiResponse<CartItemsEntity> response = cartItemService.createCartItem(cartItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartItemById(@PathVariable long id) {
        ApiResponse<CartItemsEntity> response = cartItemService.getCartItemById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCartItem(@PathVariable long id, @RequestBody CartItemsEntity updatedCartItem) {
        ApiResponse<CartItemsEntity> response = cartItemService.updateCartItem(id, updatedCartItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCartItem(@PathVariable long id) {
        ApiResponse<String> response = cartItemService.deleteCartItem(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
