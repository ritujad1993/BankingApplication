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

@RestController
@RequestMapping("/bank/accounts")
public class AccountController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	TransactionService transactionService;
	
	@GetMapping
	public ResponseEntity<?> getAllAccountsInfo(){
		List<Account> accountList = accountService.getAllAccounts();
		
		if(accountList.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Accounts data exists");
		}
		else {
			return ResponseEntity.status(HttpStatus.OK).body(accountList);
		}
	}
	
	@PostMapping("/openAccount")
	public ResponseEntity<?> openAccount(@RequestBody Map<String, Object> requestBody) {
		String customerID = (String) requestBody.get("customerID");
		double initialCredit = (double) requestBody.get("initialCredit");
		
		try {
		Account account = accountService.openAccount(customerID, initialCredit);
		
		if(initialCredit > 0) {
			Transaction transaction = transactionService.addTransaction(new Transaction(account.getAccountID(), initialCredit));
			account.getTransactions().add(transaction);
		}
		return ResponseEntity.status(HttpStatus.OK).body(account);
		}catch(RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}
}
