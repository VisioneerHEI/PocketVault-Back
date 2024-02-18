package com.wallet.pocketvault_back.Service;

import com.wallet.pocketvault_back.Entity.TransferHistory;
import com.wallet.pocketvault_back.Repository.TransferHistoryDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TransferHistoryService {
    public final TransferHistoryDAO transferHistoryDAO;

    public TransferHistoryService(TransferHistoryDAO transferHistoryDAO) {
        this.transferHistoryDAO = transferHistoryDAO;
    }

    public List<TransferHistory> getAllTransferHistory() {
        try {
            return transferHistoryDAO.findAll();
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching all transfer histories", e);
        }
    }

    public List<TransferHistory> getTransactionHistoryByUser(int idUser) {
        try {
            return transferHistoryDAO.findByUserId(idUser);
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching transaction histories by user ID", e);
        }
    }

    public List<TransferHistory> getTransactionHistoryByDate(Timestamp transferDate) {
        try {
            return transferHistoryDAO.findByDate(transferDate);
        } catch (SQLException e) {
            throw new RuntimeException("Error while fetching transaction histories by date", e);
        }
    }
}
