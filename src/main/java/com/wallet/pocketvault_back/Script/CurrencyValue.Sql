CREATE TABLE IF NOT EXISTS CurrencyValue (
   idValue SERIAL PRIMARY KEY,
   sourceCurrencyId INT,
   destinationCurrencyId INT,
   amount DOUBLE PRECISION,
   dateEffet TIMESTAMP,
    "limit" DOUBLE PRECISION,
   FOREIGN KEY (sourceCurrencyId) REFERENCES Currency(currencyId),
   FOREIGN KEY (destinationCurrencyId) REFERENCES Currency(currencyId)
);
