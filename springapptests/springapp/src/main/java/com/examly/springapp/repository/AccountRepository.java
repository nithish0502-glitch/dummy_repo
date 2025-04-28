package com.examly.springapp.repository;

import com.examly.springapp.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {

   // List<Account> findActiveAccountsOfVerifiedCustomers();

   // List<Account> findByCustomerName(String customerName);
}

