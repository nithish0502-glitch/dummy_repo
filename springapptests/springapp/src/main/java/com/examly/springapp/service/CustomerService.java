package com.examly.springapp.service;

import com.examly.springapp.model.Customer;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    void checkAccess(Customer customer) throws Exception, Throwable;
}


