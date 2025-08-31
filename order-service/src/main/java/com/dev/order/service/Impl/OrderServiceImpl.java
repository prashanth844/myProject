package com.dev.order.service.Impl;

import com.dev.order.service.dto.OrderDto;
import com.dev.order.service.dto.OrderResponseDto;
import com.dev.order.service.enumFile.OrderStatus;
import com.dev.order.service.model.Order;
import com.dev.order.service.repository.OrderRepsitory;
import com.dev.order.service.service.OrderService;
import com.dev.common_dto.mapper.DtoEntityMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	private final OrderRepsitory orderRepository;

	private final DtoEntityMapper<OrderDto, Order> orderDtoMapper;

	private final DtoEntityMapper<OrderResponseDto, Order> orderResponseMapper;

	@Autowired
	public OrderServiceImpl(OrderRepsitory orderRepository) {
		this.orderRepository = orderRepository;
		this.orderDtoMapper = DtoEntityMapper.getDtoEntityMapper();
		this.orderResponseMapper = DtoEntityMapper.getDtoEntityMapper();
	}

	@Override
	public OrderResponseDto placeOrder(OrderDto orderDto) {
		Order order = orderDtoMapper.convertFromDtoToEntity(orderDto, Order.class);
		Order saved = orderRepository.save(order);
		return orderResponseMapper.convertFromEntityToDto(saved, OrderResponseDto.class);
	}

	@Override
	public OrderResponseDto getOrderById(String orderId) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
		return orderResponseMapper.convertFromEntityToDto(order, OrderResponseDto.class);
	}

	@Override
	public List<OrderResponseDto> getOrdersByUser(String userId) {
		return orderRepository.findByUserId(userId).stream()
				.map(order -> orderResponseMapper
				.convertFromEntityToDto(order, OrderResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderResponseDto> getAllOrders() {
		return orderRepository.findAll().stream()
				.map(order -> orderResponseMapper
				.convertFromEntityToDto(order, OrderResponseDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public OrderResponseDto updateOrderStatus(String orderId, String status) {
		Order order = orderRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

		OrderStatus orderStatus = OrderStatus.valueOf(status.toUpperCase());

		order.setStatus(orderStatus);
		Order updated = orderRepository.save(order);

		return orderResponseMapper.convertFromEntityToDto(updated, OrderResponseDto.class);
	}

}
