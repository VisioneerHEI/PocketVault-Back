package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.Account;
import com.wallet.pocketvault_back.Entity.Transaction;
import com.wallet.pocketvault_back.Repository.AccountDAO;

import java.sql.SQLException;

public class TransactionService {
    private final AccountDAO accountDAO;

    public TransactionService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void transferMoney(Transaction transaction) {
        try {
            Account sourceAccount = accountDAO.findById(transaction.getSourceAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Source account not found"));
            Account destinationAccount = accountDAO.findById(transaction.getDestinationAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Destination account not found"));

            if (sourceAccount.getBalance() < transaction.getAmount()) {
                throw new IllegalArgumentException("Insufficient funds");
            }

            sourceAccount.setBalance(sourceAccount.getBalance() - transaction.getAmount());
            destinationAccount.setBalance(destinationAccount.getBalance() + transaction.getAmount());

            accountDAO.save(sourceAccount);
            accountDAO.save(destinationAccount);
        } catch (SQLException e) {
            throw new RuntimeException("Error while transferring money", e);
        }
    }

    public void depositMoney(Transaction depositRequest) {
        try {
            Account account = accountDAO.findById(depositRequest.getAccountId())
                    .orElseThrow(() -> new IllegalArgumentException("Account not found"));

            double currentBalance = account.getBalance();
            double depositedAmount = depositRequest.getAmount();
            account.setBalance(currentBalance + depositedAmount);

            accountDAO.save(account);
        } catch (SQLException e) {
            throw new RuntimeException("Error while depositing money", e);
        }
    }
}
