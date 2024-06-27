package com.mybank.banking.entity;

/**
 * Entity class for Transaction
 */
public class Transaction {
	private Long transactionID;
	private Long accountID;
	private double transactionAmount;
	private String transactionType;

	/**
	 * @param accountID
	 * @param transactionAmount
	 */
	public Transaction(Long accountID, double transactionAmount, String transactionType) {
		super();
		this.accountID = accountID;
		this.transactionAmount = transactionAmount;
		this.transactionType = transactionType;
	}

	/**
	 * @return the transactionType
	 */
	public String getTransactionType() {
		return transactionType;
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
	
	/**
	 * @param accountID the accountID to set
	 */
	public void setAccountID(Long accountID) {
		this.accountID = accountID;
	}

	/**
	 * @param transactionAmount the transactionAmount to set
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	/**
	 * @param transactionType the transactionType to set
	 */
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	@Override
	public String toString() {
		return "Transaction [transactionID=" + transactionID + ", accountID=" + accountID + ", transactionAmount="
				+ transactionAmount + ", transactionType=" + transactionType + "]";
	}

}
