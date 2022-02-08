package com.training.MyBoutique_20.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.training.MyBoutique_20.persistence.UserEntity;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private  UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 Optional<UserEntity> user = userRepository.findByUsername(username);

	        user.orElseThrow(() -> new UsernameNotFoundException("Not found: " + username));

	        return user.map(UserEntiyDetails::new).get();
	}
	


}
