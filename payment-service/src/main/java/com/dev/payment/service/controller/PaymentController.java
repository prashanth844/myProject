package com.dev.payment.service.controller;

import com.dev.payment.service.dto.PaymentDto;
import com.dev.payment.service.enumPage.PaymentStatus;
import com.dev.payment.service.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<PaymentDto> createPayment(@RequestParam String orderId, @RequestParam String userId,
			@RequestParam double amount, @RequestParam String paymentMode) {
		PaymentDto createdPayment = paymentService.createPayment(orderId, userId, amount, paymentMode);
		return ResponseEntity.ok(createdPayment);
	}

	@PutMapping("/{id}/status")
	public ResponseEntity<PaymentDto> updatePaymentStatus(@PathVariable String id, @RequestParam PaymentStatus status) {
		PaymentDto updatedPayment = paymentService.updatePaymentStatus(id, status);
		return ResponseEntity.ok(updatedPayment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PaymentDto> getPaymentById(@PathVariable String id) {
		PaymentDto payment = paymentService.getPaymentById(id);
		return ResponseEntity.ok(payment);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<PaymentDto>> getPaymentsByUser(@PathVariable String userId) {
		List<PaymentDto> payments = paymentService.getPaymentsByUser(userId);
		return ResponseEntity.ok(payments);
	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<List<PaymentDto>> getPaymentsByOrder(@PathVariable String orderId) {
		List<PaymentDto> payments = paymentService.getPaymentsByOrder(orderId);
		return ResponseEntity.ok(payments);
	}

	@GetMapping
	public ResponseEntity<List<PaymentDto>> getAllPayments() {
		List<PaymentDto> payments = paymentService.getAllPayments();
		return ResponseEntity.ok(payments);
	}
}
