package com.training.MyBoutique_20.dto;



import com.training.MyBoutique_20.validations.ValidPassword;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@ValidPassword(message ="passwords are not equal")
public class CustomerDto {

	private Long Id;
	
	private String firstName;
	
	
	private String lastName;
	
	
	private String email;
	
	
	private String telephone;
	
	
	private String username;
	
	
	private String password;
	
	
	
	
	private  String matchingPassword;
	
	
	private String roles;


	


	




	

	
	
	
	
}
