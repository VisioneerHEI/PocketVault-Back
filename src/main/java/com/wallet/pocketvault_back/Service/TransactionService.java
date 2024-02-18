package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.Account;
import com.wallet.pocketvault_back.Entity.Transaction;
import com.wallet.pocketvault_back.Repository.AccountDAO;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
public class TransactionService {
    private AccountDAO accountDAO;

    @Transactional
    public void transferMoney(Transaction transaction) throws SQLException {
        Account sourceAccount = accountDAO.findById(transaction.getSourceAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
        Account destinationAccount = accountDAO.findById(transaction.getDestinationAccountId())
                .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

        double amount = transaction.getAmount();

        if (sourceAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        sourceAccount.setBalance(sourceAccount.getBalance() - amount);
        destinationAccount.setBalance(destinationAccount.getBalance() + amount);

        accountDAO.save(sourceAccount);
        accountDAO.save(destinationAccount);
    }

}
