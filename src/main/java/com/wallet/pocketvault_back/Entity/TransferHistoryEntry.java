package com.wallet.pocketvault_back.Entity;

import java.sql.Timestamp;
import java.util.List;

public class TransferHistoryEntry {
    private int id_historyEntry;
    private int debitTransactionId;
    private int creditTransactionId;
    private double transferAmount;
    private Timestamp transferDate;
    private List <Transaction> debitTransactions;
    private List <Transaction> creditTransactions;
}
