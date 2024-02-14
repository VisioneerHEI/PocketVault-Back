package com.wallet.pocketvault.Configuration;

import org.springframework.context.annotation.Bean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    @Bean
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                "jdbc:postgresql://localhost/pocket_vault",
                "postgres",
                "postgres"
        );
    }
}
