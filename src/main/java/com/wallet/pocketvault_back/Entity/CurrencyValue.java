package com.wallet.pocketvault_back.Entity;

import java.sql.Timestamp;

public class CurrencyValue {
    private int id_value;
    private int sourceCurrencyId;
    private int destinationCurrencyId;
    private double amount;
    private Timestamp date_effet;
    private Currency sourceCurrency;
    private Currency destinationCurrency;
}
