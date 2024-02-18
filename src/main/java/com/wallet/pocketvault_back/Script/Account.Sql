CREATE TABLE IF NOT EXISTS Account (
    accountId SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    balance DOUBLE PRECISION NOT NULL,
    accountType VARCHAR(20) CHECK (accountType IN ('Bank', 'Cash', 'Mobile money')),
    id_currency INT REFERENCES Currency (currencyId)
);
