/**
 * 
 */
package com.mybank.banking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.mybank.banking.entity.Transaction;

/**
 * 
 */
@Repository
public class TransactionRepository {

	private List<Transaction> transactions = new ArrayList<>();
	private long id = 1;
	
	public Transaction save(Transaction transaction) {
		transaction.setTransactionID(id++);
		transactions.add(transaction);
		return transaction;
	}
	
	public List<Transaction> findAll() {
		return transactions;
	}

	public Optional <List<Transaction>> findByAccountID(Long accountID){
		return  Optional.ofNullable(transactions.stream().filter(transaction -> transaction.getAccountID() == accountID).toList());
	}
}
