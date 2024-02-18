package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Configuration.DatabaseConnection;
import com.wallet.pocketvault_back.Entity.Account;
import com.wallet.pocketvault_back.Entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransactionDAO extends GenericDAO<Transaction>{
    public TransactionDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection.getConnection());
    }

    private static Transaction extractTransactionFromResultSet(ResultSet resultSet) throws SQLException {
        int transactionId = resultSet.getInt("transactionId");
        int sourceAccountId = resultSet.getInt("sourceAccountId");
        int destinationAccountId = resultSet.getInt("destinationAccountId");
        String labelTransaction = resultSet.getString("labelTransaction");
        double amount = resultSet.getDouble("amount");
        Timestamp dateOfTransaction = resultSet.getTimestamp("dateOfTransaction");
        String transactionType = resultSet.getString("transactionType");
        List<Account> accounts = new ArrayList<>();


        return new Transaction(transactionId, sourceAccountId, destinationAccountId, labelTransaction, amount, dateOfTransaction,transactionType,accounts);
    }

    @Override
    public void update(Transaction toUpdate) throws SQLException {

        String sql = "UPDATE Transaction SET sourceAccountId = ?, labelTransaction = ?, amount = ?, " +
                "destinationAccountId = ?, getDateOfTransaction = ?, transactionType = ?, accounts = ?" +
                " WHERE transactionId = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toUpdate.getLabelTransaction());
            statement.setDouble(2, toUpdate.getAmount());
            statement.setTimestamp(3, toUpdate.getDateOfTransaction());
            statement.setString(4, toUpdate.getTransactionType());
            statement.setArray(4, (Array) toUpdate.getAccounts());
            statement.setInt(5, toUpdate.getTransactionId());

            statement.executeUpdate();
        }

    }

    @Override
    public void save(Transaction toSave) throws SQLException {
        String sql = "INSERT INTO Transaction (transactionId, labelTransaction, " +
                "amount, dateOfTransaction, transactionType, accounts)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, toSave.getTransactionId());
            statement.setString(2, toSave.getLabelTransaction());
            statement.setDouble(3, toSave.getAmount());
            statement.setTimestamp(4, toSave.getDateOfTransaction());
            statement.setString(5, toSave.getTransactionType());
            statement.setInt(6, toSave.getSourceAccountId());
            statement.setInt(7, toSave.getDestinationAccountId());
            statement.setArray(8, (Array) toSave.getAccounts());
        }

    }

    @Override
    public List<Transaction> findAll() throws SQLException {
        List<Transaction> AllTransactions = new ArrayList<>();
        String sql = "SELECT * FROM Transaction";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Transaction transaction = extractTransactionFromResultSet(resultSet);
                AllTransactions.add(transaction);
            }
        }
        return AllTransactions;
    }

    @Override
    public Optional<Transaction> findById(int transactionId) throws SQLException {
        String sql = "SELECT * FROM Transaction WHERE transactionId = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, transactionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractTransactionFromResultSet(resultSet));
                }
            }
        }
        return Optional.empty();
    }

}
