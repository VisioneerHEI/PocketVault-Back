package com.wallet.pocketvault_back.Controller;

import com.wallet.pocketvault_back.Entity.Transaction;
import com.wallet.pocketvault_back.Service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;

public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transferMoney")
    public void transferMoney(@RequestBody Transaction transaction){
        transactionService.transferMoney(transaction);
    }

    @PostMapping("/depositMoney")
    public void depositMoney(@RequestBody Transaction depositRequest) {
        transactionService.depositMoney(depositRequest);
    }
}
