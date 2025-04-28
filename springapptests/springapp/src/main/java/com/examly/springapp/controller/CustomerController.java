package com.examly.springapp.controller;

import com.examly.springapp.model.Customer;
import com.examly.springapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<?> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.status(404).body("No customers found.");
        }
        return ResponseEntity.ok(customers);
    }
     
    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
        if (customer.getName() == null || customer.getName().isEmpty() || 
            customer.getPhoneNumber() == null || customer.getPhoneNumber().isEmpty()) {
            return ResponseEntity.status(409).body("Customer name and phone number are required.");
        }
        Customer createdCustomer = customerService.createCustomer(customer);
        return ResponseEntity.status(201).body(createdCustomer);
    }
     
    @PutMapping("/{id}")
    public ResponseEntity<?> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(id, customer);
        if (updatedCustomer == null) {
            return ResponseEntity.status(404).body("Customer with ID " + id + " not found.");
        }
        return ResponseEntity.ok(updatedCustomer);
    }

    @GetMapping("/activeAccounts")
    public ResponseEntity<List<Customer>> getCustomersWithActiveAccounts() {
        List<Customer> activeCustomers = customerService.getAllCustomersWithActiveAccounts();
        return ResponseEntity.ok(activeCustomers);
    }

    @GetMapping("/{unauthorized}")
    public ResponseEntity<?> unauthorizedAccess(@PathVariable String unauthorized) {
        return ResponseEntity.status(401).body("Unauthorized access to customer accounts. Customer is not verified.");
    }
}
