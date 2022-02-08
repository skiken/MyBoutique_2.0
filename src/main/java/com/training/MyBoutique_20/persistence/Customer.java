package com.training.MyBoutique_20.persistence;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@DiscriminatorValue("customer")
public class Customer extends UserEntity  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Email
	@Column(name = "email")
	private String email;
	
	@Column(name = "telephone")
	private String telephone;
	
	
	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private Set<Cart> setCart= new HashSet<>();
	
	@OneToOne(mappedBy = "customer")
	private VerificationToken verificationToken;
	
   
	
	public Customer( String username, String password, String roles, boolean enabled,String firstName, String lastName, String email, String telephone, Set<Cart> setCart) {
		super(username, password, roles, enabled);
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.telephone = telephone;
		this.setCart = setCart;
		
	}
	

	
	
}
