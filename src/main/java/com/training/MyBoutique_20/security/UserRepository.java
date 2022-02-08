package com.training.MyBoutique_20.security;

import org.springframework.stereotype.Repository;

import com.training.MyBoutique_20.persistence.UserEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
	 Optional< UserEntity> findByUsername(String username);
}
