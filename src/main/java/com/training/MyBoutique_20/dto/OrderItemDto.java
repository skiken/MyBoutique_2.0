package com.training.MyBoutique_20.dto;






import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@AllArgsConstructor
public class OrderItemDto {

	private Long id;
	private Long quantity;
	
	private Long productId;

	private Long orderId;
}
