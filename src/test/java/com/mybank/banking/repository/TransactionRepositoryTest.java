package com.mybank.banking.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Transaction;

public class TransactionRepositoryTest {
	
	@InjectMocks
	TransactionRepository transactionRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		transactionRepository.save(new Transaction(1L, 100.00));
		transactionRepository.save(new Transaction(2L, 5000.00));
	}
	
	@Test
	public void testFindAll() {

		List<Transaction> resultList = transactionRepository.findAll();
		assertNotNull(resultList);
		assertEquals(2,resultList.size());
	}
	
	@Test
	public void testfindByAccountID() {		
		Optional<List<Transaction>> resultList = transactionRepository.findByAccountID(1L);
		
		assertTrue(resultList.isPresent());
		assertNotNull(resultList.get());
		assertEquals(1,resultList.get().size());
	}
}
