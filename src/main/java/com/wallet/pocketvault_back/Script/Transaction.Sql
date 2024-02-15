CREATE TABLE IF NOT EXISTS Transaction (
    transactionId SERIAL PRIMARY KEY,
    labelTransaction VARCHAR(255),
    amount DOUBLE PRECISION NOT NULL,
    dateOfTransaction TIMESTAMP NOT NULL,
    transactionType VARCHAR(10) NOT NULL CHECK (transactionType IN ('Debit', 'Credit')),
    id_account INT REFERENCES Account(id_account)
);