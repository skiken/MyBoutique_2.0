package com.training.MyBoutique_20.dto;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {

	private Long Id;
	
	private BigDecimal totalPrice;

	
	private String status;


	private ZonedDateTime shipped;


	private AddressDto shipmentAddress;

	
	private PaymentDto paymentDto;

	
	private Set<OrderItemDto> orderItems;
}
