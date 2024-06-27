package com.mybank.banking.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Transaction;
import com.mybank.banking.service.AccountService;
import com.mybank.banking.service.TransactionService;

/**
 * Rest Controller for all Account endpoints
 */
@RestController
@RequestMapping("/bank/accounts")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	AccountService accountService;

	@Autowired
	TransactionService transactionService;

	/**
	 * Get endpoint to get list of all accounts
	 * 
	 * @return ResponseEntity with details of accounts if exists else error message
	 */
	@GetMapping
	public ResponseEntity<?> getAllAccountsInfo() {
		logger.info("Getting details for all accounts");
		List<Account> accountList = accountService.getAllAccounts();

		if (accountList.isEmpty()) {
			logger.error("No accounts found in the database");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Accounts data exists");
		} else {
			logger.info("Retrived data for {} accounts ", accountList.size());
			return ResponseEntity.status(HttpStatus.OK).body(accountList);
		}
	}

	/**
	 * Post endpoint to open a new account for a customer
	 * 
	 * @param requestBody map containing customerID and initialCredit for opening
	 *                    account
	 * @return ResponseEntity with the created account if customer exists else error
	 *         message
	 */
	@PostMapping("/openAccount")
	public ResponseEntity<?> openAccount(@RequestBody Map<String, Object> requestBody) {
		String customerID = (String) requestBody.get("customerID");
		double initialCredit = (double) requestBody.get("initialCredit");
		logger.info("Opening new account for CustomerId: {} with initial credit: {} ", customerID, initialCredit);
		try {
			Account account = accountService.openAccount(customerID, initialCredit);

			if (initialCredit > 0) {
				Transaction transaction = transactionService
						.addTransaction(new Transaction(account.getAccountID(), initialCredit));
				account.getTransactions().add(transaction);
			}

			logger.info("New account created for Customer: {}", customerID);
			return ResponseEntity.status(HttpStatus.OK).body(account);

		} catch (RuntimeException e) {
			logger.error("Exception occured while opening new account: {}", e);
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}

	}
}
