package com.examly.springapp.repository;

import com.examly.springapp.model.Account;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccountRepository extends JpaRepository<Account, Long> {

   // List<Account> findActiveAccountsOfVerifiedCustomers();

   // List<Account> findByCustomerName(String customerName);
   @Query("SELECT a FROM Account a WHERE a.customer.name = :name")
    Account findByCustomerName(@Param("name") String name);
}

