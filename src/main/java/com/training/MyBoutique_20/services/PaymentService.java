package com.training.MyBoutique_20.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.PaymentDto;
import com.training.MyBoutique_20.persistence.Order;
import com.training.MyBoutique_20.persistence.Payment;
import com.training.MyBoutique_20.persistence.enums.PaymentStatus;
import com.training.MyBoutique_20.repository.OrderRepository;
import com.training.MyBoutique_20.repository.PaymentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class PaymentService {
	
	@Resource
	private OrderRepository orderRepository;
	@Resource
	private PaymentRepository paymentRepository;
	
	public List<PaymentDto> findAll() {
		log.debug("Request to get all Payments");
		return this.paymentRepository.findAll()
		.stream()
		.map(PaymentService::mapToDto)
		.collect(Collectors.toList());
		
	}
	
	@Transactional(readOnly = true)
	public PaymentDto findById(Long id) {
	log.debug("Request to get Payment : {}", id);
	return this.paymentRepository.findById(id).map(PaymentService::mapToDto).orElse(null);
	}
	
	public PaymentDto create(PaymentDto paymentDto) {
		Order order = this.orderRepository.findById(paymentDto.getOrderId()).orElse(null);
		return mapToDto(paymentRepository.save(new
				Payment(
						paymentDto.getPaypalPaymentId(),
						PaymentStatus.valueOf(paymentDto.getStatus()),
						order
						)))
				;
		
	}
	public void delete(Long id) {
		log.debug("Request to delete Payment : {}", id);
		this.paymentRepository.deleteById(id);
		}

	public static PaymentDto mapToDto(Payment payment) {
		
		if (payment!=null) {
			return new PaymentDto(payment.getId(),
					payment.getPaypalPaymentId(),
					payment.getStatus().name(),
					payment.getOrder().getId());
			
		} return null;
	}
}
