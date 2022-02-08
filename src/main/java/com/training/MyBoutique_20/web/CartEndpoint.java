package com.training.MyBoutique_20.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyBoutique_20.dto.CartDto;
import com.training.MyBoutique_20.services.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api"+"/carts")
public class CartEndpoint {

	@Autowired
	private CartService cartService;
	
	@GetMapping("/List")
	public List<CartDto> findAll() {
		return  cartService.findAll();
	}
	
	@GetMapping("/active")
    public List<CartDto> findAllActiveCarts(){
    	return cartService.findActiveCart();
    }
	
	@GetMapping("/{id}")
	public CartDto findById(@PathVariable("id") Long  id) {
		return  cartService.findById(id);
	}
	
    @PostMapping("/customer/{id}")
	public CartDto create(@PathVariable ("id") Long customerId) {
		return cartService.create(customerId);
		
	}
    
    @DeleteMapping("/cart/{id}")
    public void delete(@PathVariable("id")Long id) {
    	
    	this.cartService.delete(id);
    }
    
	
}
