package com.training.MyBoutique_20.services;



import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.annotation.Resource;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.CustomerDto;
import com.training.MyBoutique_20.persistence.Customer;
import com.training.MyBoutique_20.persistence.VerificationToken;
import com.training.MyBoutique_20.repository.CustomerRepository;

import com.training.MyBoutique_20.repository.VerificationTokenRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@Transactional
@RequiredArgsConstructor

public class CustomerService {
	
	@Resource
	private  CustomerRepository customerRepository;
	
	@Resource
	private VerificationTokenRepository verificationTokenRepository;
	
	
	public VerificationToken  createVerificationToken(String email) {
		log.debug("request to create new verification token for the customer by email customer,{}",email);
		Customer customer= customerRepository.findByEmail(email);
				return verificationTokenRepository.save(new VerificationToken(
				UUID.randomUUID().toString(),customer));
	}

	
	
	public CustomerDto create(CustomerDto customerDto)  {
		log.debug("request to create customer :{}",customerDto);
		return mapToDto(
			    this.customerRepository.save
				(new Customer(
			    customerDto.getUsername(),
			    customerDto.getPassword(),
				customerDto.getRoles(),	
				Boolean.FALSE,
				customerDto.getFirstName(),
				customerDto.getLastName(),
				customerDto.getEmail(),
				customerDto.getTelephone(),
				Collections.emptySet()
		
				)));
	}
	
	
	public void activateAcount(Customer customer)  {
		log.debug("request to activation account :{}",customer);
		customer.setEnabled(true);
		this.customerRepository.save(customer);

				
	}
	
	
	public Customer findByEmail(CustomerDto customerDto) {
		log.debug("request to find customer by email");
		return customerRepository.findByEmail(customerDto.getEmail());
		}
	
	public List<CustomerDto> findAll(){
		log.debug("request to find all customers");
		return this.customerRepository.findAll().stream().map(CustomerService::mapToDto).collect(Collectors.toList());
		
	}
	public List<CustomerDto> findAllActive() {
		log.debug("Request to get all Customers");
		return this.customerRepository.findAllByEnabled(true)
		.stream()
		.map(CustomerService::mapToDto)
		.collect(Collectors.toList());
		}
	
	public List<CustomerDto> findAllInactive() {
		log.debug("Request to get all Customers");
		return this.customerRepository.findAllByEnabled(false)
		.stream()
		.map(CustomerService::mapToDto)
		.collect(Collectors.toList());
		}
	
	public CustomerDto findCustomerById(Long id) {
		log.debug("request to find customer:{}",id);
		return this.customerRepository.findById(id).map(CustomerService::mapToDto).orElseThrow(()->new IllegalStateException(
				"the customer don't exist"));
		
	}
	
	

	public void deleteCustomer(Long id) {
		log.debug("request to delete customer : {}",id);
		this.customerRepository.findById(id).orElseThrow(()->new IllegalStateException(
				"the customer don't exist"));
		
		customerRepository.deleteById(id);
		
	}
	
	public static CustomerDto mapToDto(Customer customer) {
		if(customer!=null) {
			return new CustomerDto(
					customer.getId(), 
					customer.getFirstName(), 
					customer.getLastName(),
					customer.getEmail(),
					customer.getTelephone(),
					customer.getUsername(),
					customer.getPassword(),
					customer.getPassword(),
					customer.getRoles()
					);
		}
		return null;
	}
	
	
	
	
}
