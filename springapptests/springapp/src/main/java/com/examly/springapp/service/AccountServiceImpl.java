package com.examly.springapp.service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.exception.UnauthorizedAccessException;
import com.examly.springapp.model.Account;
import com.examly.springapp.model.Customer;
import com.examly.springapp.repository.AccountRepository;
import com.examly.springapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account createAccount(Long customerId, Account account) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException());
        if (!Boolean.TRUE.equals(customer.getIsVerified())) {
            throw new UnauthorizedAccessException("Customer is not verified.");
        }
        account.setCustomer(customer);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + id + " not found."));
    }

    @Override
    public List<Account> getAccountsByCustomerName(String customerName) {
        return accountRepository.findByCustomerName(customerName);
    }

    @Override
    public List<Account> getActiveAccountsOfVerifiedCustomers() {
        return accountRepository.findActiveAccountsOfVerifiedCustomers();
    }

    @Override
    public void deleteAccount(Long id) {
        Account account = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with ID " + id + " not found."));
        accountRepository.delete(account);
    }
}
