package com.mybank.banking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.AccountRepository;
import com.mybank.banking.repository.CustomerRepository;

public class AccountServiceTest {
	
	@InjectMocks
	AccountService accountService;
	
	@Mock
	AccountRepository accountRepository;
	
	@Mock 
	CustomerRepository customerRepository;
	
	private Customer customer;
	private Account account;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		customer = new Customer("John","Doe","1"); 
		account = new Account("1", 100.00);
		
		
	}

	@Test
	public final void testOpenAccount() {
		when(customerRepository.findByCustomerID("1")).thenReturn(Optional.ofNullable(customer));
		when(accountRepository.save(any(Account.class))).thenReturn(account);
		
		Account result = accountService.openAccount("1", 100.00);
		
		assertEquals("1", result.getCustomerID());
		assertEquals(100.00, result.getBalance());
		
	}
	
	@Test()
	public final void testOpenAccountException() {
		when(customerRepository.findByCustomerID(any(String.class))).thenReturn(Optional.empty());
		
		RuntimeException exception = assertThrows(RuntimeException.class, () -> accountService.openAccount("1", 100.00));
		
		assertEquals("Customer Not found", exception.getMessage());
		
		
	}

	@Test
	public final void testGetAllAccounts() {
		List<Account> accountList = new ArrayList<>();
		accountList.add(account);
		when(accountRepository.findAll()).thenReturn(accountList);
		
        List<Account> resultList = accountService.getAllAccounts();
		
		assertNotNull(resultList);
		assertEquals(accountList,resultList);
		
	}

	@Test
	public final void testGetAccountsByCustomerID() {
		Optional<List<Account>> accountList = Optional.of(new ArrayList<>());
		accountList.get().add(account);
		when(accountRepository.findByCustomerID("1")).thenReturn(accountList);
		
		List<Account> resultList = accountService.getAccountsByCustomerID("1");
		
		assertNotNull(resultList);
		assertEquals(accountList.get(),resultList);
	}

}
