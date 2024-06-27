package com.mybank.banking.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.controller.CustomerController;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.CustomerRepository;

/**
 * Service class for Customer related operations
 */
@Service
public class CustomerService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerRepository customerRepository;

	/**
	 * Method to get list of all the customers present in the database
	 * 
	 * @return list of customers
	 */
	public List<Customer> getAllCustomers() {
		logger.info("Getting details for all customers");
		return customerRepository.findAll();
	}

	/**
	 * Method to find a customer by its CustomerID
	 * 
	 * @param id the CustomerID of customer
	 * @return an Optional containing Customer details of found or else return empty
	 */
	public Optional<Customer> findCustomerById(String id) {
		logger.info("Find customer by id: {}", id);
		return customerRepository.findByCustomerID(id);
	}

	/**
	 * Method to create a new customer
	 * 
	 * @param customer customer details to be saved in database
	 */
	public void addCustomer(Customer customer) {
		customerRepository.save(customer);
		logger.info("Customer information saved for : {}", customer.getCustomerID());
	}

}
