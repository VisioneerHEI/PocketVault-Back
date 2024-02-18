package com.wallet.pocketvault_back.Controller;

import com.wallet.pocketvault_back.Entity.TransferHistory;
import com.wallet.pocketvault_back.Service.TransferHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
public class TransferHistoryController {
    private final TransferHistoryService transferHistoryService;

    public TransferHistoryController(TransferHistoryService transferHistoryService) {
        this.transferHistoryService = transferHistoryService;
    }

    @GetMapping("/transferHistory/all")
    public List<TransferHistory> getAllTransferHistory() {
        return transferHistoryService.getAllTransferHistory();
    }

    @GetMapping("/transferHistory/byUser")
    public List<TransferHistory> getTransferHistoryByUser(@RequestParam("idUser") int userId) {
        return transferHistoryService.getTransactionHistoryByUser(userId);
    }

    @GetMapping("/transferHistory/byDate")
    public List<TransferHistory> getTransferHistoryByDate(@RequestParam("transferDate") Timestamp transferDate) {
        return transferHistoryService.getTransactionHistoryByDate(transferDate);
    }
}
