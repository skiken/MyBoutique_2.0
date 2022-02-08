package com.training.MyBoutique_20.web;


import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyBoutique_20.dto.CustomerDto;
import com.training.MyBoutique_20.persistence.Customer;
import com.training.MyBoutique_20.services.CustomerService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api" + "/customer")
@Slf4j
public class CustomerEndpoint {

	@Autowired
	CustomerService customerService;


	

	@PostMapping("/create")
	@ApiOperation(value="creation of a new customer")
	public CustomerDto create(@RequestBody @Valid CustomerDto customerDto) throws Exception{
		log.debug("request to the root of creation new customer ");
		  Customer customer = customerService.findByEmail(customerDto);
	        if(customer!=null) 
	        {
	           throw new Exception(" email already exist");
	        }
	        else
	        {
		return customerService.create(customerDto);
	        }
	}
	
	

	@GetMapping()
	public List<CustomerDto> findAll() {
		return customerService.findAll();

	}

	@GetMapping("/Id")
	@ApiOperation(value="creation of a new customer",notes="provide an id of customer",response = Customer.class)
	public CustomerDto findById(@ApiParam(value="the id is required ",required = true)@RequestParam("id") Long id) {
		return customerService.findCustomerById(id);

	}

	@DeleteMapping("/Id/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.customerService.deleteCustomer(id);

	}

	@GetMapping("/username")

	public String currentUserName(Principal principal) {
		return principal.getName();
	}
	
	

}
