package com.dev.order.service.service;

import java.util.List;

import com.dev.order.service.dto.OrderDto;
import com.dev.order.service.dto.OrderResponseDto;

public interface OrderService {
	
    OrderResponseDto placeOrder(OrderDto orderDto);

    OrderResponseDto getOrderById(String orderId);

    List<OrderResponseDto> getOrdersByUser(String userId);

    List<OrderResponseDto> getAllOrders();

    OrderResponseDto updateOrderStatus(String orderId, String status);
}