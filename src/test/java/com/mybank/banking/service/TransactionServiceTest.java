package com.mybank.banking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Transaction;
import com.mybank.banking.repository.TransactionRepository;

public class TransactionServiceTest {

	@InjectMocks
	TransactionService transactionService;

	@Mock
	TransactionRepository transactionRepository;

	private Transaction transaction;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		transaction = new Transaction(1L, 100.00, "Credit");
	}

	@Test
	public void testAddTransaction() {
		transactionService.addTransaction(transaction);
		verify(transactionRepository).save(transaction);
	}

	@Test
	public void testGetAllTransaction() {
		List<Transaction> transactionList = new ArrayList<>();
		transactionList.add(transaction);
		when(transactionRepository.findAll()).thenReturn(transactionList);

		List<Transaction> resultList = transactionService.getAllTransaction();

		assertNotNull(resultList);
		assertEquals(transactionList, resultList);
	}

	@Test
	public void testGetTransactionsByAccountID() {
		Optional<List<Transaction>> transactionList = Optional.of(new ArrayList<>());
		transaction.setTransactionID(1L);
		transactionList.get().add(transaction);
		when(transactionRepository.findByAccountID(1L)).thenReturn(transactionList);

		List<Transaction> resultList = transactionService.getTransactionsByAccountID(1L);

		assertNotNull(resultList);
		assertEquals(transactionList.get(), resultList);
	}
}
