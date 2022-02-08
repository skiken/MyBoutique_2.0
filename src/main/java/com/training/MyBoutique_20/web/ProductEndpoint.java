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

import com.training.MyBoutique_20.dto.ProductDto;
import com.training.MyBoutique_20.services.ProductService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api"+"/products")
public class ProductEndpoint {
	
	@Autowired
	private  ProductService productService;
	
	@GetMapping
	public List<ProductDto> findAll() {
	return this.productService.findAll();
	}
	@GetMapping("/{id}")
	public ProductDto findById(@PathVariable Long id) {
	return this.productService.findById(id);
	}
	@PostMapping
	public ProductDto create(@RequestBody ProductDto productDto) {
	return this.productService.create(productDto);
	}
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
	this.productService.delete(id);
	}
}
