package com.examly.springapp.controller;

import com.examly.springapp.model.Customer;
import com.examly.springapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer savedCustomer = customerService.createCustomer(customer);
        return new ResponseEntity<>(savedCustomer, HttpStatus.CREATED);
    }

    // Endpoint for testing access based on verification
    @PostMapping("/access/denied")
    public ResponseEntity<String> checkAccess(@RequestBody Customer customer) {
        customerService.checkAccess(customer);
        return new ResponseEntity<>("Access granted", HttpStatus.OK);
    }
}
