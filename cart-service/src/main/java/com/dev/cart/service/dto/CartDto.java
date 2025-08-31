package com.dev.cart.service.dto;

import java.util.ArrayList;
import java.util.List;

public class CartDto {
    private Long id;
    private String userId;
    private List<CartItemDto> items = new ArrayList<>();
    private Double totalPrice;
    private Integer totalItems;
    private Boolean checkedOut;

    public CartDto() {}

    public Long getId() { 
    	return id; 
    	}
    public void setId(Long id) { 
    	this.id = id; 
    	}

    public String getUserId() { 
    	return userId; 
    	}
    public void setUserId(String userId) {
    	this.userId = userId;
    	}

    public List<CartItemDto> getItems() { 
    	return items; 
    	}
    public void setItems(List<CartItemDto> items) { 
    	this.items = items;
    	}

    public Double getTotalPrice() {
    	return totalPrice;
    	}
    public void setTotalPrice(Double totalPrice) { 
    	this.totalPrice = totalPrice;
    	}

    public Integer getTotalItems() {
    	return totalItems; 
    	}
    public void setTotalItems(Integer totalItems) { 
    	this.totalItems = totalItems; 
    	}

    public Boolean getCheckedOut() {
    	return checkedOut;
    	}
    public void setCheckedOut(Boolean checkedOut) { 
    	this.checkedOut = checkedOut; 
    	}
}
