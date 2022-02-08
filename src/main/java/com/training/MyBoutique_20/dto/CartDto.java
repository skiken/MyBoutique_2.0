package com.training.MyBoutique_20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDto {
	private Long Id;
	private String status;
	private Long orderId;
	private CustomerDto customerDto;
	
	

}
