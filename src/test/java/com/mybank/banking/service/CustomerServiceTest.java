package com.mybank.banking.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.mybank.banking.entity.Customer;
import com.mybank.banking.repository.CustomerRepository;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customer = new Customer("John","Doe","1");

    }

    @Test
    public void testFindCustomerById() {
        when(customerRepository.findByCustomerID("1")).thenReturn(Optional.of(customer));
        Optional<Customer> result = customerService.findCustomerById("1");
        assertTrue(result.isPresent());
        assertEquals(customer, result.get());
    }

    @Test
    public void testSaveCustomer() {
        customerService.addCustomer(customer);
        verify(customerRepository, times(1)).save(customer);
    }
    
    
    @Test
    public void testGetAllCustomers() {
    	List<Customer> customerList = new ArrayList<>();
    	customerList.add(customer);
    	when(customerRepository.findAll()).thenReturn(customerList);
    	List<Customer> resultList = customerService.getAllCustomers();
    	assertNotNull(resultList);
    	assertEquals(1, resultList.size());
    	assertEquals("1", resultList.get(0).getCustomerID());
    	assertEquals("John", resultList.get(0).getFirstName());
    	assertEquals("Doe", resultList.get(0).getLastName());
    	
    }
}
