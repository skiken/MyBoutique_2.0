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

import com.training.MyBoutique_20.dto.OrderDto;
import com.training.MyBoutique_20.services.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api"+"/orders")
public class OrderEndpoint {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/create")
	public OrderDto create(@RequestBody OrderDto orderDto) {
		return orderService.create(orderDto);
		
	}
	
	@GetMapping()
	public List<OrderDto> findAll(){
		return orderService.findAll();
	}

	@GetMapping("/id/{id}")
	public OrderDto findById(@PathVariable Long id) {
		return orderService.findOrderById(id);
	}
	
	@DeleteMapping("/id/{id}")
	public void delete(@PathVariable Long id) {
		
		this.orderService.delete(id);
	}
}
