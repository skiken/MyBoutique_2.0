package com.training.MyBoutique_20.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.OrderItemDto;
import com.training.MyBoutique_20.persistence.Order;
import com.training.MyBoutique_20.persistence.OrderItem;
import com.training.MyBoutique_20.persistence.Product;
import com.training.MyBoutique_20.repository.OrderItemRepository;
import com.training.MyBoutique_20.repository.OrderRepository;
import com.training.MyBoutique_20.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class OrderItemService {
	
	@Resource
	private OrderItemRepository orderItemRepository;
	@Resource
	private OrderRepository orderRepository;
	@Resource
	private ProductRepository productRepository;
	
	public List<OrderItemDto> findAll() {
		log.debug("request to get all orderItems");
		return orderItemRepository.findAll().stream().map(OrderItemService::mapToDto).collect(Collectors.toList());
		
	}
	
	public OrderItemDto findById(Long id) {
		log.debug("request to get orderItem : {}",id);
		return orderItemRepository.findById(id).map(OrderItemService::mapToDto).orElse(null);
		
	}
	
	public OrderItemDto create(OrderItemDto orderItemDto) {
		log.debug("request to create orderItem:{}",orderItemDto);
		Order order= this.orderRepository.findById(orderItemDto.getOrderId()).orElseThrow(()->new IllegalStateException("order not found"));
		Product product= this.productRepository.findById(orderItemDto.getProductId()).orElseThrow(()->new IllegalStateException("product not found"));
		return mapToDto(this.orderItemRepository.save(
				new OrderItem(
				orderItemDto.getQuantity(),
				product,
				order
				)
				));	
		}
		

	public void delete(Long id) {
		log.debug("request to delete orderItem: {}",id);
		orderItemRepository.deleteById(id);
	}

	public static OrderItemDto mapToDto(OrderItem orderItem) {
		if(orderItem!=null) {
			return new OrderItemDto(orderItem.getId(),
					orderItem.getQuantity(),
					orderItem.getProduct().getId(),
					orderItem.getOrder().getId());
		}
		return null;
	}
}
