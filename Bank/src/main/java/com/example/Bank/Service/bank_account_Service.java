package com.example.Bank.Service;

/*import com.example.Bank.DAO.bank_accounts_DAO;
import com.example.Bank.Entities.bank_accounts;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class bank_account_Service {
    @Autowired
    private bank_accounts_DAO bankAccountDAO;

    public List<bank_accounts> listBankAccounts() {
        return bankAccountDAO.findAll();
    }

    @Transactional(rollbackFor = BankTransactionException.class)
    public void transferMoney(Integer fromAccountId, Integer toAccountId, BigDecimal amount) throws BankTransactionException {
        BankAccount fromAccount = bankAccountDAO.findById(fromAccountId)
                .orElseThrow(() -> new BankTransactionException("Account not found: " + fromAccountId));
        BankAccount toAccount = bankAccountDAO.findById(toAccountId)
                .orElseThrow(() -> new BankTransactionException("Account not found: " + toAccountId));

        if (fromAccount.getBalance().compareTo(amount) < 0) {
            throw new BankTransactionException("Insufficient balance in account: " + fromAccountId);
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(amount));
        toAccount.setBalance(toAccount.getBalance().add(amount));

        bankAccountDAO.save(fromAccount);
        bankAccountDAO.save(toAccount);
    }
}*/
