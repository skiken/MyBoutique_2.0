package com.training.MyBoutique_20.persistence;


import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;

import javax.persistence.Table;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.training.MyBoutique_20.persistence.abstracts.AbstractEntity;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "UserEntity")
@DiscriminatorColumn

public  class UserEntity extends AbstractEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "username")
	@JsonIgnore
	private String username;
	
	
	@Column(name="password")
	@JsonIgnore
	private String password;
	
	
	@Column(name="roles")
	@JsonIgnore
	private String roles;
	
	@Column(name="enabled")
	@JsonIgnore
	private boolean enabled;
	
	
	
	
	
	

	
	
	
	

}
