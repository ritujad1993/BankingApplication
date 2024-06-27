package com.mybank.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.controller.CustomerController;
import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.AccountRepository;
import com.mybank.banking.repository.CustomerRepository;

/**
 * Service class for Account related operations
 */
@Service
public class AccountService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	AccountRepository accountRepository;

	@Autowired
	CustomerRepository customerRepository;

	/**
	 * Method to create a new account for a customer with the provided initial
	 * credit and also create a new transaction if initial credit is more than 0
	 * 
	 * @param customerID    for which the accounts need to be created
	 * @param initialCredit initial balance in the account
	 * @return account that is created
	 */
	public Account openAccount(String customerID, double initialCredit) {
		Optional<Customer> customer = customerRepository.findByCustomerID(customerID);

		if (customer.isEmpty()) {
			logger.error("No data found for {}", customerID);
			throw new RuntimeException("Customer Not found");
		}

		Account account = accountRepository.save(new Account(customerID, initialCredit));
		logger.info("Account created with ID: {}", account.getAccountID());
		return account;
	}

	/**
	 * Method to get list of all the accounts present in the database
	 * 
	 * @return list of accounts
	 */
	public List<Account> getAllAccounts() {
		logger.info("Getting details for all accounts");
		return accountRepository.findAll();
	}

	/**
	 * Method to get list of all accounts for a particular customer
	 * 
	 * @param customerID for which account details is fetched
	 * @return list of accounts for that customer
	 */
	public List<Account> getAccountsByCustomerID(String customerID) {
		logger.info("Retrieving accounts for customer ID: {}", customerID);
		return accountRepository.findByCustomerID(customerID).orElse(new ArrayList<>());
	}
	
	/**
	 * Method to retrieve account for a particular accountID
	 * 
	 * @param accountID for which account details is fetched
	 * @return an Optional containing Account details of found or else return empty
	 */
	public Optional<Account> getAccountByAccountID(Long accountID) {
		logger.info("Retrieving account details for account ID: {}", accountID);
		return accountRepository.findByAccountID(accountID);
	}
}
