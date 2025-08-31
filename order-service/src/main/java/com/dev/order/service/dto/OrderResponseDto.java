package com.dev.order.service.dto;

import java.util.List;

import com.dev.order.service.enumFile.OrderStatus;

public class OrderResponseDto {

    private String id;
    private String userId;
    private double totalPrice;
    private OrderStatus status;
    private List<OrderItemDto> items;

    public OrderResponseDto() {
    	
    }

    public OrderResponseDto(String id, String userId, double totalPrice, OrderStatus status, List<OrderItemDto> items) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.status = status;
        this.items = items;
    }

    
    public String getId() { 
    	return id; 
    	}
    public void setId(String id) {
    	this.id = id; 
    	}

    public String getUserId() { 
    	return userId; 
    	}
    public void setUserId(String userId) { 
    	this.userId = userId;
    	}

    public double getTotalPrice() { 
    	return totalPrice;
    	}
    public void setTotalPrice(double totalPrice) { 
    	this.totalPrice = totalPrice; 
    	}

    public OrderStatus getStatus() {
    	return status;
    	}
    public void setStatus(OrderStatus status) {
    	this.status = status;
    	}

    public List<OrderItemDto> getItems() { 
    	return items; 
    	}
    public void setItems(List<OrderItemDto> items) { 
    	this.items = items;
    	}
}
