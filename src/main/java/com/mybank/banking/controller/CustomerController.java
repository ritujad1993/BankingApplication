package com.mybank.banking.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mybank.banking.entity.Account;
import com.mybank.banking.entity.Customer;
import com.mybank.banking.entity.Transaction;
import com.mybank.banking.service.AccountService;
import com.mybank.banking.service.CustomerService;

@RestController
@RequestMapping("bank/customers")
public class CustomerController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerService customerSrvice;
	
	@Autowired
	private AccountService accountService;
	
	@GetMapping
	public ResponseEntity<?>  getAllCustomersInfo(){
		List<Customer> customerList = customerSrvice.getAllCustomers();
		logger.info("Getting list of all Customers");
		if(customerList.isEmpty()) {	
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Customer data exists");
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(customerList);
		}
	}
	
	@GetMapping("/{customerID}")
	public ResponseEntity<?> getCustomerInfoById(@PathVariable String customerID){
		Optional<Customer> customer = customerSrvice.findCustomerById(customerID);
		if(customer.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer does not exists");
		}else {
			List<Account> accountList = accountService.getAccountsByCustomerID(customerID);
			double totalBalance = accountList.stream().mapToDouble(Account::getBalance).sum();
			List<Transaction> transactionList = accountList.stream().flatMap(account -> account.getTransactions().stream()).collect(Collectors.toList());
			
			Map<String, Object> customerInfo = new HashMap<>();
			customerInfo.put("Name", customer.get().getFirstName());
			customerInfo.put("Surname", customer.get().getLastName());
			customerInfo.put("Balance", totalBalance);
			customerInfo.put("Transactions", transactionList);
			return ResponseEntity.status(HttpStatus.OK).body(customerInfo);
		}
	}
	
	@PostMapping("/addCustomer")
	public ResponseEntity<String> addCustomer(@RequestBody Customer customer){
		customerSrvice.addCustomer(customer);
		return ResponseEntity.status(HttpStatus.OK).body("Created customer "+ customer.getCustomerID());
	}
}
