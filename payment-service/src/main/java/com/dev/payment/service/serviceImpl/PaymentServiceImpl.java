package com.dev.payment.service.serviceImpl;

import com.dev.common_dto.mapper.DtoEntityMapper;
import com.dev.payment.service.dto.PaymentDto;
import com.dev.payment.service.enumPage.PaymentStatus;
import com.dev.payment.service.exception.PaymentNotFoundException;
import com.dev.payment.service.model.Payment;
import com.dev.payment.service.repository.PaymentRepository;
import com.dev.payment.service.service.PaymentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;

	private final DtoEntityMapper<PaymentDto, Payment> mapper;

	public PaymentServiceImpl(PaymentRepository paymentRepository) {
		this.paymentRepository = paymentRepository;
		this.mapper = DtoEntityMapper.getDtoEntityMapper();
	}

	@Override
	public PaymentDto createPayment(String orderId, String userId, double amount, String paymentMode) {
		PaymentDto dto = new PaymentDto();
		dto.setOrderId(orderId);
		dto.setUserId(userId);
		dto.setAmount(amount);
		dto.setPaymentMode(paymentMode);
		dto.setStatus(PaymentStatus.PENDING);

		Payment payment = mapper.convertFromDtoToEntity(dto, Payment.class);
		Payment saved = paymentRepository.save(payment);

		return mapper.convertFromEntityToDto(saved, PaymentDto.class);
	}

	@Override
	public PaymentDto updatePaymentStatus(String paymentId, PaymentStatus status) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

		payment.setStatus(status);
		Payment updated = paymentRepository.save(payment);

		return mapper.convertFromEntityToDto(updated, PaymentDto.class);
	}

	@Override
	public PaymentDto getPaymentById(String paymentId) {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new PaymentNotFoundException("Payment not found with id: " + paymentId));

		return mapper.convertFromEntityToDto(payment, PaymentDto.class);
	}

	@Override
	public List<PaymentDto> getPaymentsByUser(String userId) {
		return paymentRepository.findByUserId(userId).stream()
				.map(payment -> mapper.convertFromEntityToDto(payment, PaymentDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PaymentDto> getPaymentsByOrder(String orderId) {
		return paymentRepository.findByOrderId(orderId).stream()
				.map(payment -> mapper.convertFromEntityToDto(payment, PaymentDto.class)).collect(Collectors.toList());
	}

	@Override
	public List<PaymentDto> getAllPayments() {
		return paymentRepository.findAll().stream()
				.map(payment -> mapper.convertFromEntityToDto(payment, PaymentDto.class)).collect(Collectors.toList());
	}
}
