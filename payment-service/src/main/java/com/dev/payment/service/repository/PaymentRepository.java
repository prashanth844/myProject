package com.dev.payment.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.payment.service.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
	
    List<Payment> findByUserId(String userId);
    List<Payment> findByOrderId(String orderId);
}
