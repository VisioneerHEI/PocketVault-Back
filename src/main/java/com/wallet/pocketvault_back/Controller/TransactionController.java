package com.wallet.pocketvault_back.Controller;

import com.wallet.pocketvault_back.Entity.Transaction;
import com.wallet.pocketvault_back.Service.TransactionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transferMoney")
    public void transferMoney(@RequestBody Transaction transaction) throws SQLException {
        transactionService.transferMoney(transaction);
    }
}
