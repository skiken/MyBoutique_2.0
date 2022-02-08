package com.training.MyBoutique_20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.MyBoutique_20.persistence.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	List<Customer> findAllByEnabled(Boolean enabled);
	Customer findByEmail(String email);
	
	

}
