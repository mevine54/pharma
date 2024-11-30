package fr.mevine.util;

import fr.mevine.exceptions.DatabaseException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe utilitaire pour gérer les connexions JDBC à la base de données.
 */
public class DatabaseConnection {

    // URL de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/GestionPharmacie";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    /**
     * Méthode utilitaire pour obtenir une connexion JDBC.
     *
     * @return Une instance de {@link Connection}.
     * @throws DatabaseException En cas d'échec de connexion.
     */
    public static Connection getConnection() {
        try {
            // Chargement du pilote JDBC (nécessaire pour certaines versions de Java)
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Établissement de la connexion
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new DatabaseException("Pilote JDBC MySQL non trouvé.", e);
        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la connexion à la base de données.", e);
        }
    }

    /**
     * Méthode utilitaire pour fermer une connexion.
     *
     * @param connection La connexion à fermer.
     */
    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                throw new DatabaseException("Erreur lors de la fermeture de la connexion.", e);
            }
        }
    }
}
