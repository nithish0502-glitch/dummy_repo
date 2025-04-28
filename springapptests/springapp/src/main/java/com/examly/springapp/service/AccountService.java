package com.examly.springapp.service;

import com.examly.springapp.model.Account;
import java.util.List;

public interface AccountService {
    Account createAccount(Long customerId, Account account);
    Account getAccountById(Long id);
    // List<Account> getActiveAccountsOfVerifiedCustomers();
    void deleteAccount(Long id);
    public Account getAccountByCustomerName(String customerName);

}
