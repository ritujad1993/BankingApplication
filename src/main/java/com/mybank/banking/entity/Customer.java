package com.mybank.banking.entity;

/**
 * Entity class for Customer
 */
public class Customer {
	private String firstName;
	private String lastName;
	private String customerID;

	/**
	 * @param firstName
	 * @param lastName
	 * @param customerID
	 */
	public Customer(String firstName, String lastName, String customerID) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.customerID = customerID;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}

	@Override
	public String toString() {
		return "Customer [firstName=" + firstName + ", lastName=" + lastName + ", customerID=" + customerID + "]";
	}
}
