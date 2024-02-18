package com.wallet.pocketvault_back.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Transaction {
    private int transactionId;
    private int sourceAccountId;
    private int destinationAccountId;
    private String labelTransaction;
    private double amount;
    private Timestamp dateOfTransaction;
    private String transactionType;
    private List<Account> accounts;
}
