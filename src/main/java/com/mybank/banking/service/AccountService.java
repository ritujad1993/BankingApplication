/**
 * 
 */
package com.mybank.banking.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.AccountRepository;
import com.mybank.banking.repository.CustomerRepository;

/**
 * 
 */
@Service
public class AccountService {

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	//TransactionRepository transactionRepository = new TransactionRepository();
	
	public Account openAccount(String customerID, double initialCredit) {
		Optional<Customer> customer = customerRepository.findByCustomerID(customerID);
		
		if(customer.isEmpty()) {
			throw new RuntimeException("Customer Not found");
		}
		
		Account account = accountRepository.save(new Account(customerID,initialCredit));
//		if(initialCredit > 0) {
//			Transaction transaction = transactionRepository.save(new Transaction(account.getAccountID(), initialCredit));
//			account.getTransactions().add(transaction);
//		}
		
		return account;
	}
	
	public List<Account> getAllAccounts(){
		return accountRepository.findAll();
	}
	
	public List<Account> getAccountsByCustomerID(String customerID){
		return accountRepository.findByCustomerID(customerID).orElse(new ArrayList<>());
	}
}
