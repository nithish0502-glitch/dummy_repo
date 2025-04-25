package com.examly.springapp.service;

import com.examly.springapp.model.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Long customerId, Account account);
    Account getAccountById(Long id);
    List<Account> getAccountsByCustomerName(String customerName);
    List<Account> getActiveAccountsOfVerifiedCustomers();
    void deleteAccount(Long id);
}
