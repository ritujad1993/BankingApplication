package com.mybank.banking.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Account;

public class AccountRepositoryTest {

	@InjectMocks
	AccountRepository accountRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		accountRepository.save(new Account("1", 100.00));
		accountRepository.save(new Account("2", 1000.00));
	}
	
	@Test
	public void testFindAll() {
		List<Account> resultList = accountRepository.findAll();
		assertNotNull(resultList);
		assertEquals(2,resultList.size());
	}
	
	@Test
	public void testFindByCustomerID() {
		 Optional <List<Account>> resultList = accountRepository.findByCustomerID("1");
		 
		 assertTrue(resultList.isPresent());
		 assertNotNull(resultList.get());
		 assertEquals(1,resultList.get().size());
	}
	
	@Test
	public void testFindByAccountID() {
		Account account = accountRepository.save(new Account("1", 1000.00));
		Optional<Account> result = accountRepository.findByAccountID(account.getAccountID());
		
		assertTrue(result.isPresent());
	}
}
