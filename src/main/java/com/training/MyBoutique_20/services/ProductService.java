package com.training.MyBoutique_20.services;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.ProductDto;
import com.training.MyBoutique_20.persistence.Product;
import com.training.MyBoutique_20.persistence.enums.ProductStatus;
import com.training.MyBoutique_20.repository.CategoryRepository;
import com.training.MyBoutique_20.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProductService {
	
	@Resource
	private ProductRepository productRepository;
	@Resource
	private CategoryRepository categoryRepository;
	
	public List<ProductDto> findAll() {
		log.debug("Request to get all Products");
		return this.productRepository.findAll()
		.stream()
		.map(ProductService::mapToDto)
		.collect(Collectors.toList());
		}
	
		@Transactional(readOnly = true)
		public ProductDto findById(Long id) {
		log.debug("Request to get Product : {}", id);
		return this.productRepository.findById(id).map(ProductService::mapToDto).orElse(null);
		}
		
		public ProductDto create(ProductDto productDto) {
			
			return mapToDto(productRepository.save(
					new Product(
							productDto.getName(),
							productDto.getDescription(),
							productDto.getPrice(),
							productDto.getQuantity(),
							ProductStatus.valueOf(productDto.getStatus()),
							productDto.getSalesCounter(),
							this.categoryRepository.findById(productDto.getCategoryId()).orElse(null),
							Collections.emptySet(),
							Collections.emptySet()
							)
					));
		}
		
		public void delete(Long id) {
			log.debug("Request to delete Product : {}", id);
			this.productRepository.deleteById(id);
			}
	
	public static ProductDto mapToDto(Product product) {
		if(product!=null) {
			return new ProductDto(product.getId(),
					product.getName(),
					product.getDescription(),
					product.getPrice(),
					product.getQuantity(), 
					product.getStatus().name(),
					product.getSalesCounter(), 
					product.getCategory().getId(),
					product.getSetReview().stream().map(ReviewService::mapToDto).collect(Collectors.toSet()),
					product.getSetOrderItems().stream().map(OrderItemService::mapToDto).collect(Collectors.toSet())
					);
		}return null;
	}

}
