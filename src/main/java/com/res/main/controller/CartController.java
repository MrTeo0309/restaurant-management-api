package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.CartEntity;
import com.res.main.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<?> getAllCarts() {
        ApiResponse<List<CartEntity>> response = cartService.getAllCarts();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody CartEntity cart) {
        ApiResponse<CartEntity> response = cartService.createCart(cart);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCartById(@PathVariable long id) {
        ApiResponse<CartEntity> response = cartService.getCartById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable long id, @RequestBody CartEntity updatedCart) {
        ApiResponse<CartEntity> response = cartService.updateCart(id, updatedCart);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable long id) {
        ApiResponse<String> response = cartService.deleteCart(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
