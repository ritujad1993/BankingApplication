package com.mybank.banking.entity;

import java.util.List;

/**
 * Entity class for Account
 */
public class Account {

	private Long accountID;
	private String customerID;
	private double balance;
	private List<Transaction> transactions;

	/**
	 * @param customerID
	 * @param balance
	 */
	public Account(String customerID, double balance) {
		super();
		this.customerID = customerID;
		this.balance = balance;
	}

	/**
	 * @return the accountID
	 */
	public Long getAccountID() {
		return accountID;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	/**
	 * @return the balance
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * @return the transactions
	 */
	public List<Transaction> getTransactions() {
		return transactions;
	}

	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	/**
	 * @param transactions the transactions to set
	 */
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", customerID=" + customerID + ", balance=" + balance
				+ ", transactions=" + transactions + "]";
	}

}
