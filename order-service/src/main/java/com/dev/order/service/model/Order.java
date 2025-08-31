package com.dev.order.service.model;

import com.dev.order.service.enumFile.OrderStatus;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id; 
    
    private String userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "order_id") 
    private List<OrderItem> items;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PENDING;

    public Order() {}

    public Order(String id, String userId, List<OrderItem> items, OrderStatus status) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.status = status;
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

    public List<OrderItem> getItems() { 
    	return items; 
    	}
    public void setItems(List<OrderItem> items) { 
    	this.items = items; 
    	}

    public OrderStatus getStatus() { 
    	return status; 
    	}
    public void setStatus(OrderStatus status) { 
    	this.status = status;
    	}
}
