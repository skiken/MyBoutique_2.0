package com.training.MyBoutique_20.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.training.MyBoutique_20.persistence.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Long>{

}
