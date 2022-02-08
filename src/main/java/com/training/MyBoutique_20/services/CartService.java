package com.training.MyBoutique_20.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.training.MyBoutique_20.dto.CartDto;
import com.training.MyBoutique_20.persistence.Cart;
import com.training.MyBoutique_20.persistence.Customer;
import com.training.MyBoutique_20.persistence.Order;
import com.training.MyBoutique_20.persistence.enums.CartStatus;
import com.training.MyBoutique_20.repository.CartRepository;
import com.training.MyBoutique_20.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Transactional
@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

	@Resource
	private CartRepository cartRepository;
	@Resource
	private CustomerRepository customerRepository;
	@Resource
	private OrderService orderService;

	@Transactional(readOnly = true)
	public List<CartDto> findAll() {
		log.debug("request to get all carts");
		return this.cartRepository.findAll().stream().map(CartService::mapToDto).collect(Collectors.toList());

	}

	@Transactional(readOnly = true)
	public List<CartDto> findActiveCart() {
		log.debug("request to find active cart");
		return this.cartRepository.findByCartStatus(CartStatus.NEW).stream().map(CartService::mapToDto)
				.collect(Collectors.toList());
	}

	public CartDto findById(Long id) {
		log.debug("request to find cart :{}", id);
		return this.cartRepository.findById(id).map(CartService::mapToDto).orElse(null);
	}

	public CartDto create(Long customerId) {
		log.debug("request to create cart for customer:{}", customerId);
		if (this.getActiveCart(customerId) == null) {
			Customer customer = this.customerRepository.findById(customerId)
					.orElseThrow(() -> new IllegalStateException("The Customer does not exist!"));
			Cart cart = new Cart(CartStatus.NEW, null, customer);
			Order order = this.orderService.create(cart);
			cart.setOrder(order);
			return mapToDto(this.cartRepository.save(cart));
		} else {
			throw new IllegalStateException("There is already an active cart");
		}

	}

	public CartDto getActiveCart(Long customerId) {
		List<Cart> carts = this.cartRepository.findByCartStatusAndCustomerId(CartStatus.NEW, customerId);
		if (carts != null) {
			if (carts.size() == 1) {
				return mapToDto(carts.get(0));
			}
			if (carts.size() > 1) {
				throw new IllegalStateException("Many active carts detected !!!");
			}
		}
		return null;
	}

	public static CartDto mapToDto(Cart cart) {
		if (cart != null) {
			return new CartDto(cart.getId(), cart.getCartStatus().name(), cart.getOrder().getId(),
					CustomerService.mapToDto(cart.getCustomer()));
		}
		return null;
	}
	
	public void delete(Long id) {
		log.debug("Request to delete Cart : {}", id);
		Cart cart =this.cartRepository.findById(id).orElseThrow(()-> new IllegalStateException("cart not found"));
		cart.setCartStatus(CartStatus.CANCELED);
		this.cartRepository.save(cart);
	}

}
