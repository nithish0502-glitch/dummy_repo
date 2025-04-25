package com.examly.springapp.controller;

import com.examly.springapp.model.Account;
import com.examly.springapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/{customerId}")
    public ResponseEntity<Account> createAccount(@PathVariable Long customerId, @RequestBody Account account) {
        Account created = accountService.createAccount(customerId, account);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
        Account account = accountService.getAccountById(id);
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @GetMapping("/byCustomer/{name}")
    public ResponseEntity<List<Account>> getAccountsByCustomer(@PathVariable String name) {
        return new ResponseEntity<>(accountService.getAccountsByCustomerName(name), HttpStatus.OK);
    }

    @GetMapping("/activeAccounts")
    public ResponseEntity<List<Account>> getActiveAccounts() {
        return new ResponseEntity<>(accountService.getActiveAccountsOfVerifiedCustomers(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return new ResponseEntity<>("Account deleted successfully.", HttpStatus.OK);
    }

    @PostMapping("/access/denied")
    public ResponseEntity<String> simulateAccessDenied(@RequestBody Account dummy) {
        //throw new UnauthorizedAccessException();
        throw new IllegalArgumentException("Unauthorized access to customer accounts.");
    }
}
