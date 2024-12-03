package com.example.Bank.repository;


import com.example.Bank.Entities.bank_accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bankaccountsRepository  extends JpaRepository<bank_accounts, Long> {
}