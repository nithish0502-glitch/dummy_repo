package com.examly.springapp.service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    // Constructor injection of the repository
    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        // Save the customer and return the saved entity
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        // Fetch all customers from the database
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        // Fetch customer by ID and throw an exception if not found
        return customerRepository.findById(id);
    }

    @Override
    public Customer updateCustomer(Long id, Customer customerDetails) {
        // Fetch the existing customer by ID
        Optional<Customer> existingCustomerOpt = getCustomerById(id);
        
        // Check if the customer exists
        if (!existingCustomerOpt.isPresent()) {
            throw new RuntimeException("Customer not found with id " + id);
        }

        // Get the customer from Optional
        Customer existingCustomer = existingCustomerOpt.get();

        // Update customer details
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhoneNumber(customerDetails.getPhoneNumber());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setIsVerified(customerDetails.getIsVerified());

        // Save and return the updated customer
        return customerRepository.save(existingCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        // Check if the customer exists before deletion
        Optional<Customer> existingCustomerOpt = getCustomerById(id);
        
        // If the customer is not found, throw an exception
        if (!existingCustomerOpt.isPresent()) {
            throw new RuntimeException("Customer not found with id " + id);
        }

        // Delete the customer
        customerRepository.delete(existingCustomerOpt.get());
    }

    // @Override
    // public List<Customer> getAllCustomersWithActiveAccounts() {
    //     // Implement logic to return customers with active accounts if required
    //     return customerRepository.findCustomersWithActiveAccounts(); // Assuming a custom query method exists
    // }

    @Override
    public void checkAccess(Customer customer) throws Throwable {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'checkAccess'");
    }
}
