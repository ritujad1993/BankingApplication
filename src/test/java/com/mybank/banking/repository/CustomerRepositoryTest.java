package com.mybank.banking.repository;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Customer;

public class CustomerRepositoryTest {

	@InjectMocks
	CustomerRepository customerRepository;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		
	}
	
	@Test
	public void testFindAll() {
		List<Customer> customerList = customerRepository.findAll();
		assertNotNull(customerList);
		assertEquals(1,customerList.size());
	}
	
	@Test
	public void testFindByCustomerID() {
		customerRepository.save(new Customer("John","Doe","JDoe"));
		Optional<Customer> result = customerRepository.findByCustomerID("JDoe");
		assertTrue(result.isPresent());
	}
}
