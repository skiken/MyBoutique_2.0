package com.training.MyBoutique_20.dto;

import java.util.Set;



import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryDto {

	private Long Id;
	private String name;

	private String description;
	
	private Set<ProductDto> products;
}
