CREATE TABLE IF NOT EXISTS TransferHistoryEntry (
    idHistoryEntry SERIAL PRIMARY KEY,
     debitTransactionId INT,
    creditTransactionId INT,
    transferAmount DOUBLE PRECISION,
    transferDate TIMESTAMP,
    FOREIGN KEY (debitTransactionId) REFERENCES Transaction(transactionId),
    FOREIGN KEY (creditTransactionId) REFERENCES Transaction(transactionId)
);