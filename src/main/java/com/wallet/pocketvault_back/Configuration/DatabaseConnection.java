package com.wallet.pocketvault_back.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

import static com.wallet.pocketvault_back.Configuration.DataConfig.*;

@Configuration
public class DatabaseConnection {
    private String url;
    private String username;

    private String password;

    private static DatabaseConnection instance;
    private static Connection connection;



    private static final String URL = "jdbc:postgresql://localhost:5432/pocketvault";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "postgres";

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {

            System.out.println("Connexion à la base de données fermée.");
    }
}
