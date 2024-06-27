/**
 * 
 */
package com.mybank.banking.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Transaction;

/**
 * 
 */
@Repository
public class AccountRepository {
   
	private List<Account> accounts = new ArrayList<>();
	private long id = 1;
	
	public Account save(Account account) {	
		account.setAccountID(id++);
		account.setTransactions(new ArrayList<Transaction>());
		accounts.add(account);
		return account;
	}

	public List<Account> findAll() {
		return accounts;
	}

	public Optional <List<Account>> findByCustomerID(String customerID) {
		return Optional.ofNullable(accounts.stream().filter(c -> c.getCustomerID().equals(customerID)).collect(Collectors.toList()));
	}
	
	public Optional<Account> findByAccountID(Long accountID){
		return accounts.stream().filter(c -> c.getAccountID() == accountID).findFirst();
	}

}
