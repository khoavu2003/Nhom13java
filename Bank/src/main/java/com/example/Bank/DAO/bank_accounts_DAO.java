package com.example.Bank.DAO;

import com.example.Bank.Entities.bank_accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import  com.example.Bank.repository.bankaccountsRepository;

import java.util.List;
import java.util.Optional;


public class bank_accounts_DAO {
    @Autowired
    private bankaccountsRepository bankAccountInfoRepository;

    // Custom query method to find a bank account by account number

    public List<bank_accounts> listBankAccountInfo() {
        return bankAccountInfoRepository.findAll();
    }
}