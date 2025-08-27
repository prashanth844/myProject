package com.dev.payment.service.dto;

import com.dev.payment.service.enumPage.PaymentStatus;


public class PaymentDto {

	    private String id; 

	    private String orderId;  

	    private String userId;   

	    private double amount;

	    private PaymentStatus status = PaymentStatus.PENDING;

	    private String paymentMode;

	    public PaymentDto() {
	    	
	    }

	    public PaymentDto(String id, String orderId, String userId, double amount, PaymentStatus status, String paymentMode) {
	        this.id = id;
	        this.orderId = orderId;
	        this.userId = userId;
	        this.amount = amount;
	        this.status = status;
	        this.paymentMode = paymentMode;
	    }

	    public String getId() {
	        return id;
	    }

	    public void setId(String id) {
	        this.id = id;
	    }

	    public String getOrderId() {
	        return orderId;
	    }

	    public void setOrderId(String orderId) {
	        this.orderId = orderId;
	    }

	    public String getUserId() {
	        return userId;
	    }

	    public void setUserId(String userId) {
	        this.userId = userId;
	    }

	    public double getAmount() {
	        return amount;
	    }

	    public void setAmount(double amount) {
	        this.amount = amount;
	    }

	    public PaymentStatus getStatus() {
	        return status;
	    }

	    public void setStatus(PaymentStatus status) {
	        this.status = status;
	    }

	    public String getPaymentMode() {
	        return paymentMode;
	    }

	    public void setPaymentMode(String paymentMode) {
	        this.paymentMode = paymentMode;
	    }
}
