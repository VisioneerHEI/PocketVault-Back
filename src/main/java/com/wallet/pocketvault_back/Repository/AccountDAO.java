package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Configuration.DatabaseConnection;
import com.wallet.pocketvault_back.Entity.Account;
import com.wallet.pocketvault_back.Entity.Currency;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AccountDAO extends GenericDAO <Account>{


    public AccountDAO(DatabaseConnection databaseConnection) throws SQLException {
        super(databaseConnection.getConnection());
    }

    private static Account extractAccountFromResultSet(ResultSet resultSet) throws SQLException {
            int accountId = resultSet.getInt("accountId");
            String name = resultSet.getString("name");
            double balance = resultSet.getDouble("balance");
            String accountType = resultSet.getString("accountType");
            List<Currency> currencies = new ArrayList<>();

    return new Account(accountId, name, balance, accountType,currencies );
    }
    @Override
    public void save(Account toSave) throws SQLException {
        String sql = "INSERT INTO Account (accountId, name, balance, accountType, currencyId)" + "VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, toSave.getAccountId());
            statement.setString(2, toSave.getName());
            statement.setDouble(3, toSave.getBalance());
            statement.setString(4, toSave.getAccountType());
            statement.setArray(5, (Array) toSave.getCurrencies());
        }
    }

    @Override
    public void update(Account toUpdate) throws SQLException {

        String sql = "UPDATE Account SET name = ?, balance = ?, accountType = ?, currencyId = ? WHERE accountId = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toUpdate.getName());
            statement.setDouble(2, toUpdate.getBalance());
            statement.setString(3, toUpdate.getAccountType());
            statement.setArray(4, (Array) toUpdate.getCurrencies());
            statement.setInt(5, toUpdate.getAccountId());

            statement.executeUpdate();
        }

    }

    @Override
    public List<Account> findAll() throws SQLException {
        List<Account> AllAccounts = new ArrayList<>();
        String sql = "SELECT * FROM Account";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Account account = extractAccountFromResultSet(resultSet);
                AllAccounts.add(account);
            }
        }
        return AllAccounts;
    }

    @Override
    public Optional<Account> findById(int accountId) throws SQLException {
        String sql = "SELECT * FROM Account WHERE accountId = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, accountId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractAccountFromResultSet(resultSet));
                }
            }
        }
        return Optional.empty();
    }
}
