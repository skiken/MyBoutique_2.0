package com.training.MyBoutique_20.services;


import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.OrderDto;
import com.training.MyBoutique_20.persistence.Address;
import com.training.MyBoutique_20.persistence.Cart;
import com.training.MyBoutique_20.persistence.Order;
import com.training.MyBoutique_20.persistence.enums.OrderStatus;
import com.training.MyBoutique_20.repository.OrderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class OrderService {
	
	@Resource
	private OrderRepository orderRepository;


	
	public List<OrderDto> findAll() {
		log.debug("request to find all orders");
		return this.orderRepository.findAll().stream().map(OrderService::mapToDto).collect(Collectors.toList());
	}
	
	public OrderDto findOrderById(Long id) {
		log.debug("request to find order :{}",id);
		return this.orderRepository.findById(id).map(OrderService::mapToDto).orElse(null);
		}
	
	public OrderDto create(OrderDto orderDto) {
		log.debug("request to create order:{}",orderDto);
		return mapToDto(this.orderRepository.save(
				new Order(
				BigDecimal.ZERO,
				OrderStatus.CREATION,				
				null,
				new Address(orderDto.getShipmentAddress().getAddress1(), orderDto.getShipmentAddress().getAddress2(),
                orderDto.getShipmentAddress().getCity(), orderDto.getShipmentAddress().getPostcode(),orderDto.getShipmentAddress().getCountry()),
				null,
				null,
				Collections.emptySet()
			
				)
				));
		
	}
	public Order create(Cart cart) {
		log.debug("Request to create Order with a Cart : {}", cart);
		return this.orderRepository.save(
		new Order(
		BigDecimal.ZERO,
		OrderStatus.CREATION,
		null,
		null,
		null,
		cart,
		Collections.emptySet()
		)
		);
		}
	
	public void delete (Long id) {
		log.debug("request to delete order {}",id);
		orderRepository.deleteById(id);
	}

	public static OrderDto mapToDto(Order order) {
		if(order!=null) {
			return new OrderDto(order.getId(),
					order.getTotalPrice(),
					order.getStatus().name(),
					order.getShipped(),
					AddressService.mapToDto(order.getShipmentAddress()),
					PaymentService.mapToDto(order.getPayment()),
					order.getOrderItems().stream().map(OrderItemService::mapToDto).collect(Collectors.toSet()));
		}return null;
	}
}
