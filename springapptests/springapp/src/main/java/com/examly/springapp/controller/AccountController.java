package com.examly.springapp.controller;

import com.examly.springapp.model.Account;
import com.examly.springapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity<?> createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        Account createdAccount = accountService.createAccount(customerId, account);
        if (createdAccount == null) {
            return ResponseEntity.status(404).body("Customer with ID " + customerId + " not found.");
        }
        return ResponseEntity.status(201).body(createdAccount);
    }

    //  @GetMapping("/{id}")
    //  public ResponseEntity<?> getAccountById(@PathVariable Long id) {
    //     return accountService.getAccountById(id)
    //              .map(ResponseEntity::ok)
    //              .orElse(ResponseEntity.status(404).body("Account with ID " + id + " not found."));
    // }

    @GetMapping("/byCustomer/{customerName}")
    public ResponseEntity<Account> getAccountByCustomerName(@PathVariable String customerName) {
        Account account = accountService.getAccountByCustomerName(customerName);
        return ResponseEntity.ok(account);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@PathVariable Long id) {
        if (accountService.getAccountById(id).isPresent()) {
            accountService.deleteAccount(id);
            return ResponseEntity.ok("Account deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Account with ID " + id + " not found.");
        }
    }
}
