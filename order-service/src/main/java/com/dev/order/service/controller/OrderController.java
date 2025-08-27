package com.dev.order.service.controller;

import com.dev.order.service.dto.OrderDto;
import com.dev.order.service.dto.OrderResponseDto;
import com.dev.order.service.exception.OrderNotFoundException;
import com.dev.order.service.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;

	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@PostMapping
	public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderDto orderDto) {
		OrderResponseDto response = orderService.placeOrder(orderDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponseDto> getOrderById(@PathVariable String orderId) {
		OrderResponseDto response = orderService.getOrderById(orderId);
		if (response == null) {
			throw new OrderNotFoundException("Order not found with id: " + orderId);
		}
		return ResponseEntity.ok(response);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<OrderResponseDto>> getOrdersByUser(@PathVariable String userId) {
		List<OrderResponseDto> orders = orderService.getOrdersByUser(userId);
		return ResponseEntity.ok(orders);
	}

	@GetMapping
	public ResponseEntity<List<OrderResponseDto>> getAllOrders() {
		List<OrderResponseDto> orders = orderService.getAllOrders();
		return ResponseEntity.ok(orders);
	}

	@PutMapping("/{orderId}/status")
	public ResponseEntity<OrderResponseDto> updateOrderStatus(@PathVariable String orderId,
			@RequestParam String status) {
		OrderResponseDto response = orderService.updateOrderStatus(orderId, status);
		return ResponseEntity.ok(response);
	}
}
