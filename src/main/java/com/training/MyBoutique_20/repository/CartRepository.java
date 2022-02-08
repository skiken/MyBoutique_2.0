package com.training.MyBoutique_20.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.MyBoutique_20.persistence.Cart;
import com.training.MyBoutique_20.persistence.enums.CartStatus;




@Repository
public interface CartRepository  extends JpaRepository<Cart, Long>{

	List<Cart> findByCartStatus(CartStatus cartStatus);
	List<Cart> findByCartStatusAndCustomerId(CartStatus cartStatus, Long customerId);
}
