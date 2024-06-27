/**
 * 
 */
package com.mybank.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.CustomerRepository;

/**
 * 
 */
@Service
public class CustomerService {

	    @Autowired
	    private CustomerRepository customerRepository;

	    public List<Customer> getAllCustomers() {
	        return customerRepository.findAll();
	    }
	    
	    public Optional<Customer> findCustomerById(String id) {
	        return customerRepository.findByCustomerID(id);
	    }
	    
	    public void addCustomer(Customer customer) {
	    	customerRepository.save(customer);
	    }
	    

}
