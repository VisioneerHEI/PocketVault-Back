package com.wallet.pocketvault_back.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CurrencyValue {
    private int idValue;
    private int sourceCurrencyId;
    private int destinationCurrencyId;
    private double amount;
    private Timestamp dateEffet;
    private Currency sourceCurrency;
    private Currency destinationCurrency;
}
