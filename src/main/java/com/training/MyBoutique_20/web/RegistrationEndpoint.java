package com.training.MyBoutique_20.web;

import java.io.IOException;
import java.util.Calendar;
import javax.persistence.criteria.Predicate;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.MyBoutique_20.dto.CustomerDto;
import com.training.MyBoutique_20.persistence.Customer;
import com.training.MyBoutique_20.persistence.VerificationToken;
import com.training.MyBoutique_20.repository.VerificationTokenRepository;
import com.training.MyBoutique_20.services.CustomerService;
import com.training.MyBoutique_20.services.EmailSenderService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/registration")
public class RegistrationEndpoint {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	@PostMapping(value = "/register")
	public CustomerDto registerUser(@RequestBody @Valid CustomerDto customerDto) throws Exception {
		log.debug("request to register new customer");
		Customer customer = customerService.findByEmail(customerDto);
		if (customer != null) {
			throw new Exception("email already exist");
		} else {
			this.customerService.create(customerDto);
			VerificationToken verificationToken = customerService.createVerificationToken(customerDto.getEmail());
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(customerDto.getEmail());
				mailMessage.setSubject("Complete Registration!");
				mailMessage.setFrom("AzizRH");
				mailMessage.setText("To confirm your account, please click here : "
						+ "http://localhost:8080/registration/confirm-account?token=" + verificationToken.getToken());

				emailSenderService.sendEmail(mailMessage);
			}

		return customerDto;
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public String confirmUserAccount(@RequestParam("token") String confirmationToken) throws Exception {
		log.debug("request to redirect to the confirm-account link");
		Calendar cal = Calendar.getInstance();
		VerificationToken token = verificationTokenRepository.findByToken(confirmationToken);

		if (token != null) {
			if ((token.getExpireDate().getTime() - cal.getTime().getTime()) <= 0) {
				customerService.deleteCustomer(token.getCustomer().getId());
				verificationTokenRepository.delete(token);
				return "time expired";
			} else {
				Customer customer = customerService.findByEmail(CustomerService.mapToDto(token.getCustomer()));
				if (customer != null) {
					this.customerService.activateAcount(customer);

				}

				else
					throw new Exception("invalid token");
			}
		} else

			throw new Exception("The link is invalid or broken!");

		return "registration with  success";
	}


}
