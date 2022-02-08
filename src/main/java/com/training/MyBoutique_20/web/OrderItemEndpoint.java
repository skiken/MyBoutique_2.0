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

import com.training.MyBoutique_20.dto.OrderItemDto;
import com.training.MyBoutique_20.services.OrderItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api"+"/orderitems")
public class OrderItemEndpoint {

	
	@Autowired
	private  OrderItemService itemService;
	
	@GetMapping
	public List<OrderItemDto> findAll() {
	return this.itemService.findAll();
	}
	@GetMapping("/{id}")
	public OrderItemDto findById(@PathVariable Long id) {
	return this.itemService.findById(id);
	}
	@PostMapping
	public OrderItemDto create(@RequestBody OrderItemDto orderItemDto) {
	return this.itemService.create(orderItemDto);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	this.itemService.delete(id);
	}
}
