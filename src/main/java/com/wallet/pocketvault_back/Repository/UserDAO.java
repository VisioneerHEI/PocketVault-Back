package com.wallet.pocketvault_back.Repository;

import com.wallet.pocketvault_back.Configuration.DatabaseConnection;
import com.wallet.pocketvault_back.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserDAO extends GenericDAO <User> {

    @Autowired
    public UserDAO(DatabaseConnection databaseConnection) throws SQLException {
        super(databaseConnection.getConnection());
    }

    private static User extractUserFromResultSet(ResultSet resultSet) throws SQLException {

        int idUser = resultSet.getInt("idUser");

        String username = resultSet.getString("username");
        String password = resultSet.getString("password");
        String email = resultSet.getString("email");

        return new User(idUser, username, password, email);
    }
    @Override
    public void save(User toSave) throws SQLException {
        String sql = "INSERT INTO User(idUser, username, password, email) " +
                "VALUES (?, ?, ?, ?)";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, toSave.getIdUser());
            statement.setString(2, toSave.getUsername());
            statement.setString(3, toSave.getPassword());
            statement.setString(5, toSave.getEmail());

            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    toSave.setIdUser(generatedKeys.getInt(1));
                }
            }
        }
    }
        

    @Override
    public void update(User toUpdate) throws SQLException {
        String sql = "UPDATE User SET username = ?, password = ?, email = ? WHERE idUser = ?";


        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setString(1, toUpdate.getUsername());
            statement.setString(2, toUpdate.getPassword());
            statement.setString(3, toUpdate.getEmail());
            statement.setInt(4, toUpdate.getIdUser());

            statement.executeUpdate();
        }
    }

    @Override
    public List<User> findAll() throws SQLException {
        List<User> AllUsers = new ArrayList<>();
        String sql = "SELECT * FROM User";

        try (Statement statement = getConnection().createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                AllUsers.add(user);
            }
        }
        return AllUsers;
    }

    @Override
    public Optional<User> findById(int idUser) throws SQLException {
        String sql = "SELECT * FROM User WHERE idUser = ?";

        try (PreparedStatement statement = getConnection().prepareStatement(sql)) {
            statement.setInt(1, idUser);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(extractUserFromResultSet(resultSet));
                }
            }
        }
        return Optional.empty();
    }
}
