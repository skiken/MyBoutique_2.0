package com.training.MyBoutique_20.persistence;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import com.training.MyBoutique_20.persistence.abstracts.AbstractEntity;
import com.training.MyBoutique_20.persistence.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "orders")
public class Order extends AbstractEntity {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

@NotNull
@Column(name = "total_price", precision = 10, scale = 2, nullable = false)
private BigDecimal totalPrice;

@NotNull
@Enumerated(EnumType.STRING)
@Column(name = "status", nullable = false)
private OrderStatus status;

@Column(name = "shipped")
private ZonedDateTime shipped;


@Embedded
private Address shipmentAddress;

@OneToOne
@JoinColumn(unique = true)
private Payment payment;

@OneToOne(mappedBy = "order")
@JsonIgnore
private Cart  cart;

@OneToMany(mappedBy = "order")
@JsonIgnore
private Set<OrderItem> orderItems;


}