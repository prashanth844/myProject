package com.dev.cart.service.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "carts")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  

    private String userId;   
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CartItem> items = new ArrayList<>();

    private Double totalPrice; 

    private Integer totalItems;

    private Boolean checkedOut = false;

    public Cart() {
    	
    }


    public void addItem(CartItem item) {
        items.add(item);
        item.setCart(this);
        recalculateTotals();
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        item.setCart(null);
        recalculateTotals();
    }

    public void recalculateTotals() {
        this.totalItems = items.stream().mapToInt(CartItem::getQuantity).sum();
        this.totalPrice = items.stream().mapToDouble(i -> i.getPrice() * i.getQuantity()).sum();
    }

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

    public List<CartItem> getItems() {
    	return items; 
    	}

    public void setItems(List<CartItem> items) {
        this.items = items;
        recalculateTotals();
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
