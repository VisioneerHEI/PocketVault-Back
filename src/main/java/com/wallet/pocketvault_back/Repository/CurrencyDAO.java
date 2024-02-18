package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Configuration.DatabaseConnection;
import com.wallet.pocketvault_back.Entity.Currency;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CurrencyDAO extends GenericDAO <Currency>{
    public CurrencyDAO(DatabaseConnection databaseConnection) throws SQLException {
        super(databaseConnection.getConnection());
    }

    private static Currency extractCurrencyFromResultSet(ResultSet resultSet) throws SQLException {
        int currencyId = resultSet.getInt("currencyId");
        String currencyName = resultSet.getString("currencyName");
        String currencyCode = resultSet.getString("currencyCode");

        return new Currency(currencyId, currencyName,currencyCode);
    }

    @Override
    public void save(Currency toSave) throws SQLException {
        String sql = "INSERT INTO Currency (currencyId, currencyName, currencyCode)" + "VALUES (?, ?, ?)";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, toSave.getCurrencyId());
            statement.setString(2, toSave.getCurrencyName());
            statement.setString(3, toSave.getCurrencyCode());
        }
    }

    @Override
    public void update(Currency toUpdate) throws SQLException {
        String sql = "UPDATE Currency SET currencyName = ?, currencyCode = ? WHERE currencyId = ?";
        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toUpdate.getCurrencyName());
            statement.setString(2, toUpdate.getCurrencyCode());
            statement.setInt(3, toUpdate.getCurrencyId());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Currency> findAll() throws SQLException {
        List<Currency> AllCurrencies = new ArrayList<>();
        String sql = "SELECT * FROM Currency";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                Currency currency = extractCurrencyFromResultSet(resultSet);
                AllCurrencies.add(currency);
            }
        }
        return AllCurrencies;
    }

    @Override
    public Optional<Currency> findById(int currencyId) throws SQLException {
        String sql = "SELECT * FROM Currency WHERE currencyId = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, currencyId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractCurrencyFromResultSet(resultSet));
                }
            }
        }
        return Optional.empty();
    }
}
