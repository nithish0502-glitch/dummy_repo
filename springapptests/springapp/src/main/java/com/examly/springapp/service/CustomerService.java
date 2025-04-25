package com.examly.springapp.service;

import com.examly.springapp.model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createCustomer(Customer customer);
   
    List<Customer> getAllCustomers();
    
    Optional<Customer> getCustomerById(Long id);
    
    Customer updateCustomer(Long id, Customer customer);
    
    void deleteCustomer(Long id);
}
