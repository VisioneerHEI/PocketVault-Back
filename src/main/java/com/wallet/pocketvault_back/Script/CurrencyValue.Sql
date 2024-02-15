CREATE TABLE IF NOT EXISTS CurrencyValue (
   id_value SERIAL PRIMARY KEY,
   sourceCurrencyId INT,
   destinationCurrencyId INT,
   amount DOUBLE PRECISION,
   date_effet TIMESTAMP,
   FOREIGN KEY (sourceCurrencyId) REFERENCES Currency(id_currency),
   FOREIGN KEY (destinationCurrencyId) REFERENCES Currency(id_currency)
);
