package com.dev.order.service.model;

import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; 

    private String productId;
    private int quantity;
    private double price;

    public OrderItem() {}

    public OrderItem(String id, String productId, int quantity, double price) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public String getId() {
    	return id; 
    	}
    public void setId(String id) {
    	this.id = id; 
    	}

    public String getProductId() {
    	return productId; 
    	}
    public void setProductId(String productId) {
    	this.productId = productId; 
    	}

    public int getQuantity() {
    	return quantity; 
    	}
    public void setQuantity(int quantity) {
    	this.quantity = quantity;
    	}

    public double getPrice() {
    	return price;
    	}
    public void setPrice(double price) {
    	this.price = price; 
    	}
}
