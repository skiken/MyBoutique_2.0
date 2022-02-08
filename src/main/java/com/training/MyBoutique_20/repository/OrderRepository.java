package com.training.MyBoutique_20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.MyBoutique_20.persistence.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long> {

}
