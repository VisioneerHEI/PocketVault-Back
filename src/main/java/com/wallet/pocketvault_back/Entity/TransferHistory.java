package com.wallet.pocketvault_back.Entity;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransferHistory {
    private int idHistoryEntry;
    private User users;
    private int debitTransactionId;
    private int creditTransactionId;
    private double transferAmount;
    private Timestamp transferDate;
}
