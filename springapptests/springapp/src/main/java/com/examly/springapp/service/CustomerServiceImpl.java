package com.examly.springapp.service;

import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.CustomerRepository;
import com.examly.springapp.exception.AccountAccessDeniedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public void checkAccess(Customer customer) throws Throwable {
        if (!Boolean.TRUE.equals(customer.getIsVerified())) {
            throw new Exception("Unauthorized access to customer accounts. Verification failed.");
        }
    }
}


