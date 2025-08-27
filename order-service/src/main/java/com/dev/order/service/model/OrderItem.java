package com.dev.order.service.model;


import jakarta.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String productId;
    private int quantity;
    private double price;

    // Constructors
    public OrderItem() {}

    public OrderItem(String productId, int quantity, double price) {
        this.productId = productId;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getId() { 
    	return id;
    	}
    public void setId(Long id) { 
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
