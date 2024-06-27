package com.mybank.banking.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.entity.Transaction;
import com.mybank.banking.service.AccountService;
import com.mybank.banking.service.CustomerService;

public class CustomerControllerTest {

	@InjectMocks
	CustomerController customerController;

	@Mock
	CustomerService customerService;

	@Mock
	AccountService accountService;

	private List<Customer> customerList;

	private Customer customer;

	private Account account;

	private Transaction transaction;

	private List<Account> accountList;

	private List<Transaction> transactionList;

	@BeforeEach
	public void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		accountList = new ArrayList<>();
		transactionList = new ArrayList<>();
		customerList = new ArrayList<>();

		customer = new Customer("John", "Doe", "JDoe");
		customerList.add(customer);
	}

	@Test
	public void testGetAllCustomersInfo() {
		when(customerService.getAllCustomers()).thenReturn(customerList);

		ResponseEntity<?> response = customerController.getAllCustomersInfo();
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals(customerList, response.getBody());
	}

	@Test
	public void testGetAllCustomersInfoEmptyList() {
		when(customerService.getAllCustomers()).thenReturn(new ArrayList<>());

		ResponseEntity<?> response = customerController.getAllCustomersInfo();
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("No Customer data exists", response.getBody());
	}

	@Test
	public void testGetCustomerInfoById() {
		transaction = new Transaction(1L, 100.00, "Credit");
		transactionList.add(transaction);

		account = new Account("1", 100.00);
		account.setAccountID(1L);
		account.setTransactions(transactionList);
		accountList.add(account);

		when(customerService.findCustomerById(any(String.class))).thenReturn(Optional.of(customer));
		when(accountService.getAccountsByCustomerID(any(String.class))).thenReturn(accountList);

		ResponseEntity<?> response = customerController.getCustomerInfoById("JDoe");
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

	@Test
	public void testGetCustomerInfoByIdNotFound() {

		when(customerService.findCustomerById(any(String.class))).thenReturn(Optional.empty());

		ResponseEntity<?> response = customerController.getCustomerInfoById("JDoe");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertNotNull(response.getBody());
		assertEquals("Customer does not exists", response.getBody());
	}

	@Test
	public void testAddCustomer() {
		doNothing().when(customerService).addCustomer(any(Customer.class));

		ResponseEntity<?> response = customerController.addCustomer(new Customer("John", "Doe", "JDoe"));
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotNull(response.getBody());
	}

}
