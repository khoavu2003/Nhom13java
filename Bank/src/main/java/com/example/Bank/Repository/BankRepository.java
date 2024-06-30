package com.example.Bank.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.Bank.Entities.bank_accounts;

@Repository
public interface BankRepository extends JpaRepository<bank_accounts, Integer> {
    bank_accounts findByAccountNumber(String accountNumber);
}
