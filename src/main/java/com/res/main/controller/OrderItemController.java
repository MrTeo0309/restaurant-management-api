package com.res.main.controller;

import com.res.main.model.ApiResponse;
import com.res.main.model.OrderItemsEntity;
import com.res.main.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-items")
public class OrderItemController {

    @Autowired
    private OrderItemService orderItemService;

    @GetMapping
    public ResponseEntity<?> getAllOrderItems() {
        ApiResponse<List<OrderItemsEntity>> response = orderItemService.getAllOrderItems();
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<?> createOrderItem(@RequestBody OrderItemsEntity orderItem) {
        ApiResponse<OrderItemsEntity> response = orderItemService.createOrderItem(orderItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderItemById(@PathVariable long id) {
        ApiResponse<OrderItemsEntity> response = orderItemService.getOrderItemById(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrderItem(@PathVariable long id, @RequestBody OrderItemsEntity updatedOrderItem) {
        ApiResponse<OrderItemsEntity> response = orderItemService.updateOrderItem(id, updatedOrderItem);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderItem(@PathVariable long id) {
        ApiResponse<String> response = orderItemService.deleteOrderItem(id);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
