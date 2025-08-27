package com.dev.order.service.dto;

import java.util.List;

public class OrderDto {

    private String userId;  
    private List<OrderItemDto> items;

    public OrderDto() {}

    public OrderDto(String userId, List<OrderItemDto> items) {
        this.userId = userId;
        this.items = items;
    }

    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public List<OrderItemDto> getItems() { return items; }
    public void setItems(List<OrderItemDto> items) { this.items = items; }
}
