/**
 * 
 */
package com.mybank.banking.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.entity.Transaction;
import com.mybank.banking.repository.TransactionRepository;

/**
 * 
 */
@Service
public class TransactionService {
	
	@Autowired
	TransactionRepository transactionRepository;
	
	 public Transaction addTransaction(Transaction transaction) {
		 return transactionRepository.save(transaction);
	    }

	 
	 public List<Transaction> getAllTransaction(){
			return transactionRepository.findAll();
		}
	 
	 public List<Transaction> getTransactionsByAccountID(Long accountID){
			return transactionRepository.findByAccountID(accountID).orElse(new ArrayList<>());
		}
	 
	 
}
