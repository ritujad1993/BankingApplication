/**
 * 
 */
package com.mybank.banking.entity;

/**
 * 
 */
public class Transaction {
	private Long transactionID;
	private Long accountID;
	private double transactionAmount;

	/**
	 * @param accountID
	 * @param transactionAmount
	 */
	public Transaction(Long accountID, double transactionAmount) {
		super();
		this.accountID = accountID;
		this.transactionAmount = transactionAmount;
	}
	
	/**
	 * @return the transactionID
	 */
	public Long getTransactionID() {
		return transactionID;
	}

	/**
	 * @return the accountID
	 */
	public Long getAccountID() {
		return accountID;
	}

	/**
	 * @return the transactionAmount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}

	
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", accountID=" + accountID + ", transactionAmount="
				+ transactionAmount + "]";
	}

}
