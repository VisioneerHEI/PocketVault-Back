package com.wallet.pocketvault_back.Configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

import static com.wallet.pocketvault_back.Configuration.DataConfig.*;

@Configuration
public class DatabaseConnection {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    private static DatabaseConnection instance;
    private static Connection connection;

    public DatabaseConnection() {
    }

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(url, username, password);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database.", e);
        }
    }

    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getInstance().getConnection()) {
            System.out.println("Connexion à la base de données établie avec succès !");

            String query = "SELECT * FROM User";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(query)) {

                // Afficher les données récupérées
                while (resultSet.next()) {
                    // Exemple : affichage des valeurs des colonnes "id" et "name"
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    System.out.println("ID : " + id + ", Name : " + name);
                }
            }
            System.out.println("Connexion à la base de données fermée.");
        } catch (SQLException e) {
            throw new RuntimeException("Erreur lors de la connexion à la base de données : " + e.getMessage(), e);
        }
    }
}
