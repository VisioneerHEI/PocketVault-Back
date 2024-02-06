package com.wallet.pocketvault.Repository;

import com.wallet.pocketvault.Configuration.DatabaseConnection;
import com.wallet.pocketvault.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private final DatabaseConnection databaseConnection;

    public UserDAO(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void registerUser(User user) {
        String sql = "INSERT INTO Owner (id_owner, owner_name, password, email) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getEmail());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error registering user: ");
        }
    }

    public User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM Owner WHERE owner_name = ? AND password = ?";
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (((ResultSet) resultSet).next()) {
                return new User(
                        resultSet.getInt("id_owner"),
                        resultSet.getString("owner_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("there was an error when authenticate the username or password");
        }
        return null;
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT * FROM Owner WHERE owner_name = ?";

        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("id_owner"),
                        resultSet.getString("owner_name"),
                        resultSet.getString("password"),
                        resultSet.getString("email")
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException("there was an error when fetching owner with username : " );
        }

        return null;
    }
}
