package com.mybank.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.controller.CustomerController;
import com.mybank.banking.entity.Transaction;
import com.mybank.banking.repository.TransactionRepository;

/**
 * Service class for Transaction related operations
 */
@Service
public class TransactionService {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	TransactionRepository transactionRepository;

	/**
	 * Method to create a new transaction
	 * 
	 * @param transaction details to be added
	 * @return Transaction that is created
	 */
	public Transaction addTransaction(Transaction transaction) {
		logger.info("Creating a new transaction for account: {}", transaction.getAccountID());
		return transactionRepository.save(transaction);
	}

	/**
	 * Method to get list of all the transactions present in the database
	 * 
	 * @return list of transactions
	 */
	public List<Transaction> getAllTransaction() {
		logger.info("Fetching details for all transactions");
		return transactionRepository.findAll();
	}

	/**
	 * Method to get list of all transactions for a particular account
	 * 
	 * @param accountID for which transaction details is fetched
	 * @return list of transactions for that account
	 */
	public List<Transaction> getTransactionsByAccountID(Long accountID) {
		logger.info("Fetching transaction details for account: {} ", accountID);
		return transactionRepository.findByAccountID(accountID).orElse(new ArrayList<>());
	}

}
