CREATE TABLE IF NOT EXISTS TransferHistoryEntry (
    id_historyEntry SERIAL PRIMARY KEY,
     debitTransactionId INT,
    creditTransactionId INT,
    transferAmount DOUBLE PRECISION,
    transferDate TIMESTAMP,
    FOREIGN KEY (debitTransactionId) REFERENCES Transaction(id_transacatio),
    FOREIGN KEY (creditTransactionId) REFERENCES Transaction(id_transaction)
);