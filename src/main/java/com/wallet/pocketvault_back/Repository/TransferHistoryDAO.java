package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Configuration.DatabaseConnection;
import com.wallet.pocketvault_back.Entity.TransferHistory;
import com.wallet.pocketvault_back.Entity.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class TransferHistoryDAO extends GenericDAO<TransferHistory> {
    private final DatabaseConnection databaseConnection;

    public TransferHistoryDAO(DatabaseConnection databaseConnection) {
        super(databaseConnection.getConnection());
        this.databaseConnection = databaseConnection;
    }

    private TransferHistory extractTransferHistoryFromResultSet(ResultSet resultSet) throws SQLException {
        TransferHistory transferHistory = new TransferHistory();
        transferHistory.setId_historyEntry(resultSet.getInt("id_historyEntry"));

        UserDAO userDAO = new UserDAO(databaseConnection);
        int idUser = resultSet.getInt("idUser");
        User user = userDAO.findById(idUser).orElse(null);
        transferHistory.setUsers(user);

        int debitTransactionId = resultSet.getInt("debitTransactionId");
        transferHistory.setDebitTransactionId(debitTransactionId);

        int creditTransactionId = resultSet.getInt("creditTransactionId");
        transferHistory.setCreditTransactionId(creditTransactionId);


        transferHistory.setTransferAmount(resultSet.getDouble("transferAmount"));
        transferHistory.setTransferDate(resultSet.getTimestamp("transferDate"));

        return transferHistory;
    }


    @Override
    public void save(TransferHistory toSave) throws SQLException {
        String sql = "INSERT INTO TransferHistoryEntry (idUser, debitTransactionId, creditTransactionId, transferAmount, transferDate) " +
                "VALUES (?, ?, ?, ?, ?)";

        int idUser = toSave.getUsers().getIdUser();
        int debitTransactionId = toSave.getDebitTransactionId();
        int creditTransactionId = toSave.getCreditTransactionId();
        double transferAmount = toSave.getTransferAmount();
        Timestamp transferDate = toSave.getTransferDate();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, idUser);
            statement.setInt(2, debitTransactionId);
            statement.setInt(3, creditTransactionId);
            statement.setDouble(4, transferAmount);
            statement.setTimestamp(5, transferDate);

            statement.executeUpdate();
        }
    }

    @Override
    public void update(TransferHistory toUpdate) throws SQLException {
        String sql = "UPDATE TransferHistoryEntry " +
                "SET idUser = ?, debitTransactionId = ?, creditTransactionId = ?, transferAmount = ?, transferDate = ? " +
                "WHERE id_historyEntry = ?";

        int idUser = toUpdate.getUsers().getIdUser();
        int debitTransactionId = toUpdate.getDebitTransactionId();
        int creditTransactionId = toUpdate.getCreditTransactionId();
        double transferAmount = toUpdate.getTransferAmount();
        Timestamp transferDate = toUpdate.getTransferDate();
        int id_historyEntry = toUpdate.getId_historyEntry();

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, idUser);
            statement.setInt(2, debitTransactionId);
            statement.setInt(3, creditTransactionId);
            statement.setDouble(4, transferAmount);
            statement.setTimestamp(5, transferDate);
            statement.setInt(6, id_historyEntry);

            statement.executeUpdate();
        }
    }

    @Override
    public List<TransferHistory> findAll() throws SQLException {
        List<TransferHistory> transferHistories = new ArrayList<>();
        String sql = "SELECT * FROM TransferHistoryEntry";

        try (PreparedStatement statement = getConnection().prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                TransferHistory transferHistory = extractTransferHistoryFromResultSet(resultSet);
                transferHistories.add(transferHistory);
            }
        }
        return transferHistories;
    }

    @Override
    public Optional<TransferHistory> findById(int id_historyEntry) throws SQLException {
        String sql = "SELECT * FROM TransferHistoryEntry WHERE id_historyEntry = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, id_historyEntry);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    TransferHistory transferHistory = extractTransferHistoryFromResultSet(resultSet);
                    return Optional.of(transferHistory);
                }
            }
        }
        return Optional.empty();
    }

    public List<TransferHistory> findByUserId(int userId) throws SQLException {
        List<TransferHistory> transferHistories = new ArrayList<>();
        String sql = "SELECT * FROM TransferHistoryEntry WHERE idUser = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TransferHistory transferHistory = extractTransferHistoryFromResultSet(resultSet);
                    transferHistories.add(transferHistory);
                }
            }
        }
        return transferHistories;
    }

    public List<TransferHistory> findByDate(Timestamp transferDate) throws SQLException {
        List<TransferHistory> transferHistories = new ArrayList<>();
        String sql = "SELECT * FROM TransferHistoryEntry WHERE transferDate = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setTimestamp(1, transferDate);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    TransferHistory transferHistory = extractTransferHistoryFromResultSet(resultSet);
                    transferHistories.add(transferHistory);
                }
            }
        }
        return transferHistories;
    }

}
