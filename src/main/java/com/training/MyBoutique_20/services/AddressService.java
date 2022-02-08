package com.training.MyBoutique_20.services;

import com.training.MyBoutique_20.dto.AddressDto;
import com.training.MyBoutique_20.persistence.Address;

public class AddressService {
	public static AddressDto mapToDto(Address address) {
		if (address != null) {
		return new AddressDto(
		address.getAddress1(),
		address.getAddress2(),
		address.getCity(),
		address.getPostcode(),
		address.getCountry()
		);
		}
		return null;
		}

}
