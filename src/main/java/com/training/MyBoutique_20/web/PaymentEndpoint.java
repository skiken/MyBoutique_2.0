package com.training.MyBoutique_20.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyBoutique_20.dto.PaymentDto;
import com.training.MyBoutique_20.services.PaymentService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api"+"/payment")
@RestController
@RequiredArgsConstructor
public class PaymentEndpoint {
	
	@Autowired
	private  PaymentService paymentService;
	
	@GetMapping
	public List<PaymentDto> findAll() {
	return this.paymentService.findAll();
	}
	@GetMapping("/{id}")
	public PaymentDto findById(@PathVariable Long id) {
	return this.paymentService.findById(id);
	}
	@PostMapping
	public PaymentDto create(@RequestBody PaymentDto orderItemDto) {
	return this.paymentService.create(orderItemDto);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	this.paymentService.delete(id);
	}

}
