package com.wallet.pocketvault_back.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Account {
    private int accountId;
    private String name;
    private double balance;
    private String accountType;
    private Currency id_Currency;
}
