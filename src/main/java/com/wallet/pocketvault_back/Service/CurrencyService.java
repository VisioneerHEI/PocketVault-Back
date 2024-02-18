package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.Currency;
import com.wallet.pocketvault_back.Entity.CurrencyValue;
import com.wallet.pocketvault_back.Repository.CurrencyDAO;
import com.wallet.pocketvault_back.Repository.CurrencyValueDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private final CurrencyDAO currencyDAO;
    private CurrencyValueDAO currencyValueDAO;
    private static final int USD_ID = 1;
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

    private double convertCurrency(double amount, int sourceCurrencyId) {
        CurrencyValue currencyValue = currencyValueDAO
                .findBySourceCurrencyIdAndDestinationCurrencyId(sourceCurrencyId, CurrencyService.USD_ID)
                .orElseThrow(() -> new IllegalArgumentException("Conversion rate not found"));

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        if (currencyValue.getDateEffet().after(currentDate)) {
            throw new IllegalArgumentException("Conversion rate not valid for current date");
        }

        double exchangeRate = currencyValue.getAmount();
        return amount / exchangeRate;
    }

    public double convertCurrencyToUSD(double amount, int sourceCurrencyId) {
        try {
            return convertCurrency(amount, sourceCurrencyId);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Error while converting currency", e);
        }
    }
}