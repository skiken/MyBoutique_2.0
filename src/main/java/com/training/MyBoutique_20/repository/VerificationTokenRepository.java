package com.training.MyBoutique_20.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.MyBoutique_20.persistence.VerificationToken;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
 
    VerificationToken findByToken(String token);

}
