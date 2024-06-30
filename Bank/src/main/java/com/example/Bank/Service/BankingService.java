package com.example.Bank.Service;
import com.example.Bank.Entities.bank_accounts;
import com.example.Bank.Repository.BankRepository;
import com.example.Bank.Repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
public class BankingService {
    @Autowired
    private BankRepository bankRepository;

    public boolean authenticatePin(String accountNumber, String pinAttempt) {
        bank_accounts accounts = bankRepository.findByAccountNumber(accountNumber);
        if (accounts != null) {
            return accounts.getPin().equals(pinAttempt);
        }
        return false;
    }

    public void lockAccount(String accountNumber) {
        bank_accounts accounts = bankRepository.findByAccountNumber(accountNumber);
        if (accounts != null) {
            // Khóa tài khoản
            accounts.setIsActive(false);
            bankRepository.save(accounts);
        }
    }
}
