package com.mybank.banking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mybank.banking.entity.Customer;

/**
 * Repository class for Customer to perform all database operations
 */
@Repository
public class CustomerRepository {

	private static List<Customer> customers = new ArrayList<>();
	static {
		Customer customer = new Customer("Peter", "Parker", "PeterP");
		customers.add(customer);
	}

	public void save(Customer customer) {
		customers.add(customer);
	}

	public List<Customer> findAll() {
		return customers;
	}

	public Optional<Customer> findByCustomerID(String customerID) {
		return customers.stream().filter(c -> c.getCustomerID().equals(customerID)).findFirst();
	}

}
