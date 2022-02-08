package com.training.MyBoutique_20.persistence;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Address {
	
	@Column(name = "address_1")
	private String address1;
	
	@Column(name = "address_2")
	private String address2;
	
	@Column(name = "city")
	private String city;
	
	@Size(max = 10)
	
	@Column(name = "postcode", length = 10 )
	private String postcode;
	
	
	@Size(max = 15)
	@Column(name = "country", length = 15 )
	private String country;

}
