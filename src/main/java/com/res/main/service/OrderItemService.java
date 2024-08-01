package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.OrderItemsEntity;
import com.res.main.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    public ApiResponse<List<OrderItemsEntity>> getAllOrderItems() {
        List<OrderItemsEntity> orderItems = orderItemRepository.findAll();
        if (orderItems.isEmpty()) {
            return new ApiResponse<>(false, "No order items found", orderItems);
        }
        return new ApiResponse<>(true, "Order items retrieved successfully", orderItems);
    }

    public ApiResponse<OrderItemsEntity> createOrderItem(OrderItemsEntity orderItem) {
        OrderItemsEntity savedOrderItem = orderItemRepository.save(orderItem);
        return new ApiResponse<>(true, "Order item created successfully", savedOrderItem);
    }

    public ApiResponse<OrderItemsEntity> getOrderItemById(long id) {
        Optional<OrderItemsEntity> orderItem = orderItemRepository.findById(id);
        if (orderItem.isPresent()) {
            return new ApiResponse<>(true, "Order item retrieved successfully", orderItem.get());
        }
        return new ApiResponse<>(false, "Order item not found", null);
    }

    public ApiResponse<OrderItemsEntity> updateOrderItem(long id, OrderItemsEntity updatedOrderItem) {
        if (orderItemRepository.existsById(id)) {
            updatedOrderItem.setId(id);
            OrderItemsEntity savedOrderItem = orderItemRepository.save(updatedOrderItem);
            return new ApiResponse<>(true, "Order item updated successfully", savedOrderItem);
        }
        return new ApiResponse<>(false, "Order item not found", null);
    }

    public ApiResponse<String> deleteOrderItem(long id) {
        if (orderItemRepository.existsById(id)) {
            orderItemRepository.deleteById(id);
            return new ApiResponse<>(true, "Order item deleted successfully", "Order item deleted");
        }
        return new ApiResponse<>(false, "Order item not found", null);
    }
}
