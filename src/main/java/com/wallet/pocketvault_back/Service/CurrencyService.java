package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.Currency;
import com.wallet.pocketvault_back.Repository.CurrencyDAO;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CurrencyService {
    private final CurrencyDAO currencyDAO;

    public CurrencyService(CurrencyDAO currencyDAO) {
        this.currencyDAO = currencyDAO;
    }

    public Currency saveCurrency(Currency toSave) {
        try {
            this.currencyDAO.save(toSave);

            return toSave;
        } catch (SQLException e) {
            throw new RuntimeException("There was an error when saving the currency.");
        }
    }

    public Currency updateCurrency(Currency toUpdate) {
        try {
            this.currencyDAO.update(toUpdate);
            return toUpdate;
        } catch (SQLException e) {
            throw new RuntimeException("there was an error when updating the Currency");
        }
    }


    public List<Currency> getAllCurrencies() {
        try {
            return currencyDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching all currencies");
        }
    }

    public Optional<Currency> getCurrencyById(int id) {
        try {
            return currencyDAO.findById(id);
        } catch (SQLException e) {
            throw new RuntimeException("There has been an error when fetching currency with identification : " + id);
        }
    }
}
