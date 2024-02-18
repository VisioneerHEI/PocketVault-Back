package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Entity.CurrencyValue;

import java.util.Optional;

public class CurrencyValueDAO {
    public Optional<CurrencyValue> findBySourceCurrencyIdAndDestinationCurrencyId
            (int sourceCurrencyId, int destinationCurrencyId) {
        return Optional.empty();
    }
}
