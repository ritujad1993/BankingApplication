package com.mybank.banking.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Transaction;
import com.mybank.banking.service.AccountService;
import com.mybank.banking.service.TransactionService;

public class AccountControllerTest {

	@InjectMocks
	AccountController accountController;

	@Mock
	AccountService accountService;

	@Mock
	TransactionService transactionService;

	private Account account;

	private Transaction transaction;

	private List<Account> accountList;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		account = new Account("PParker", 100.00);
		account.setAccountID(1L);
		account.setTransactions(new ArrayList<>());
		transaction = new Transaction(1L, 100.00, "Credit");

		accountList = new ArrayList<>();
	}

	@Test
	public void testGetAllAccountsInfo() {
		accountList.add(account);
		when(accountService.getAllAccounts()).thenReturn(accountList);

		ResponseEntity<?> response = accountController.getAllAccountsInfo();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(accountList, response.getBody());

	}

	@Test
	public void testGetAllAccountsInfoEmptyList() {
		when(accountService.getAllAccounts()).thenReturn(accountList);

		ResponseEntity<?> response = accountController.getAllAccountsInfo();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("No Accounts data exists", response.getBody());

	}

	@Test
	public void testOpenAccount() {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("customerID", "PParker");
		requestBody.put("initialCredit", 100.00);

		when(accountService.openAccount("PParker", 100.00)).thenReturn(account);
		when(transactionService.addTransaction(any(Transaction.class))).thenReturn(transaction);

		ResponseEntity<?> response = accountController.openAccount(requestBody);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		Account result = (Account) response.getBody();
		assertFalse(result.getTransactions().isEmpty());
	}

	@Test
	public void testOpenAccountNoInitialCredit() {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("customerID", "PParker");
		requestBody.put("initialCredit", 0.0);

		when(accountService.openAccount("PParker", 0.0)).thenReturn(account);

		ResponseEntity<?> response = accountController.openAccount(requestBody);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		Account result = (Account) response.getBody();
		assertTrue(result.getTransactions().isEmpty());
	}

	@Test
	public void testOpenAccountException() {
		Map<String, Object> requestBody = new HashMap<>();
		requestBody.put("customerID", "PParker");
		requestBody.put("initialCredit", 0.0);

		when(accountService.openAccount("PParker", 0.0)).thenThrow(new RuntimeException("Customer Not found"));

		ResponseEntity<?> response = accountController.openAccount(requestBody);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
	}
	
	@Test
	public void testAddTransactionToAccountCredit() {
		account.getTransactions().add(transaction);
		Transaction newTransaction = new Transaction(1L, 200.00, "Credit");
		when(accountService.getAccountByAccountID(1L)).thenReturn(Optional.of(account));
		when(transactionService.addTransaction(any(Transaction.class))).thenReturn(newTransaction);

		ResponseEntity<?> response = accountController.addTransactionToAccount(newTransaction);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		Account result = (Account) response.getBody();
		assertEquals(300.00, result.getBalance());
	}
	
	@Test
	public void testAddTransactionToAccountDebit() {
		account.getTransactions().add(transaction);
		Transaction newTransaction = new Transaction(1L, 50.00, "Debit");
		when(accountService.getAccountByAccountID(1L)).thenReturn(Optional.of(account));
		when(transactionService.addTransaction(any(Transaction.class))).thenReturn(newTransaction);

		ResponseEntity<?> response = accountController.addTransactionToAccount(newTransaction);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		Account result = (Account) response.getBody();
		assertEquals(50.00, result.getBalance());
	}
	
	@Test
	public void testAddTransactionToAccountNoAccount() {	
		Transaction newTransaction = new Transaction(1L, 50.00, "Debit");
		when(accountService.getAccountByAccountID(1L)).thenReturn(Optional.empty());
		
		ResponseEntity<?> response = accountController.addTransactionToAccount(newTransaction);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Account does not exists", response.getBody());
	}

}
