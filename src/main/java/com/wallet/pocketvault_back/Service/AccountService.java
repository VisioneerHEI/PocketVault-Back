package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.Account;
import com.wallet.pocketvault_back.Repository.AccountDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public class AccountService {
    private final AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account saveAccount(Account toSave) {
        try {
            this.accountDAO.save(toSave);

            return toSave;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when saving an account.");
        }
    }

    public Account updateAccount(Account toUpdate) {
        try {
            this.accountDAO.update(toUpdate);
            return toUpdate;
        } catch (SQLException e) {
            throw new RuntimeException("there was an error when updating an account");
        }
    }


    public List<Account> getAllAccounts() {
        try {
            return accountDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all accounts");
        }
    }

    public Optional<Account> getAccountById(int id) {
        try {
            return accountDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching account with identification : " + id);
        }
    }
}
