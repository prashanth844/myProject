package com.dev.order.service.model;


import jakarta.persistence.*;
import java.util.List;

import com.dev.order.service.enumFile.OrderStatus;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id; 

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private double totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private List<OrderItem> items;

    
    public Order() {}

    public Order(String id, String userId, double totalPrice, OrderStatus status, List<OrderItem> items) {
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

    public List<OrderItem> getItems() {
    	return items;
    	}
    public void setItems(List<OrderItem> items) { 
    	this.items = items; 
    	}
}
