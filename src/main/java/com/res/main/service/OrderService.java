package com.res.main.service;

import com.res.main.model.ApiResponse;
import com.res.main.model.OrdersEntity;
import com.res.main.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public ApiResponse<List<OrdersEntity>> getAllOrders() {
        List<OrdersEntity> orders = orderRepository.findAll();
        if (orders.isEmpty()) {
            return new ApiResponse<>(false, "No orders found", orders);
        }
        return new ApiResponse<>(true, "Orders retrieved successfully", orders);
    }

    public ApiResponse<OrdersEntity> createOrder(OrdersEntity order) {
        OrdersEntity savedOrder = orderRepository.save(order);
        return new ApiResponse<>(true, "Order created successfully", savedOrder);
    }

    public ApiResponse<OrdersEntity> getOrderById(long id) {
        Optional<OrdersEntity> order = orderRepository.findById(id);
        if (order.isPresent()) {
            return new ApiResponse<>(true, "Order retrieved successfully", order.get());
        }
        return new ApiResponse<>(false, "Order not found", null);
    }

    public ApiResponse<OrdersEntity> updateOrder(long id, OrdersEntity updatedOrder) {
        if (orderRepository.existsById(id)) {
            updatedOrder.setId(id);
            OrdersEntity savedOrder = orderRepository.save(updatedOrder);
            return new ApiResponse<>(true, "Order updated successfully", savedOrder);
        }
        return new ApiResponse<>(false, "Order not found", null);
    }

    public ApiResponse<String> deleteOrder(long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return new ApiResponse<>(true, "Order deleted successfully", "Order deleted");
        }
        return new ApiResponse<>(false, "Order not found", null);
    }
}
