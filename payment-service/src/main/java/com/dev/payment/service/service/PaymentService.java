package com.dev.payment.service.service;

import com.dev.payment.service.dto.PaymentDto;
import com.dev.payment.service.enumPage.PaymentStatus;

import java.util.List;

public interface PaymentService {

    PaymentDto createPayment(String orderId, String userId, double amount, String paymentMode);

    PaymentDto updatePaymentStatus(String paymentId, PaymentStatus status);

    PaymentDto getPaymentById(String paymentId);

    List<PaymentDto> getPaymentsByUser(String userId);

    List<PaymentDto> getPaymentsByOrder(String orderId);

    List<PaymentDto> getAllPayments();
}
