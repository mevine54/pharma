package fr.mevine.dao;


import fr.mevine.exceptions.DatabaseException;
import fr.mevine.model.*;
import fr.mevine.util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe d'accès aux données pour les utilisateurs.
 */
public class UtilisateurDAO {

    /**
     * Récupère un utilisateur par son email.
     *
     * @param email L'email de l'utilisateur.
     * @return Une instance de Utilisateur ou de l'une de ses sous-classes, ou null si non trouvé.
     */
    public Utilisateur getUtilisateurParEmail(String email) {
        String query = "SELECT * FROM Utilisateur WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, email);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                // Récupération des données de l'utilisateur
                int id = resultSet.getInt("id");
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String motDePasse = resultSet.getString("motDePasse");
                String role = resultSet.getString("role");
                int adresseId = resultSet.getInt("adresse_id");
                String telephone = resultSet.getString("telephone");

                // Récupération de l'adresse associée
                Adresse adresse = getAdresseParId(adresseId);

                // Création de l'utilisateur en fonction du rôle
                Utilisateur utilisateur = creerUtilisateurSelonRole(id, nom, prenom, email, motDePasse, adresse, role, telephone);

                return utilisateur;

            } else {
                // Aucun utilisateur trouvé avec cet email
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la récupération de l'utilisateur.", e);
        }
    }

    /**
     * Crée une instance de Utilisateur ou de l'une de ses sous-classes en fonction du rôle.
     *
     * @param id          L'ID de l'utilisateur.
     * @param nom         Le nom de l'utilisateur.
     * @param prenom      Le prénom de l'utilisateur.
     * @param email       L'email de l'utilisateur.
     * @param motDePasse  Le mot de passe de l'utilisateur.
     * @param adresse     L'adresse de l'utilisateur.
     * @param role        Le rôle de l'utilisateur (manager ou utilisateur).
     * @param telephone   Le telephone de l'utilisateur.
     * @return Une instance de Utilisateur ou de l'une de ses sous-classes.
     */
    private Utilisateur creerUtilisateurSelonRole(int id, String nom, String prenom, String email, String motDePasse,
                                                  Adresse adresse, String role, String telephone) {
        if (role == null) {
            throw new DatabaseException("Le rôle de l'utilisateur ne peut pas être null.");
        }

        switch (role.toLowerCase()) {
            case "manager":
                return new Manager(id, nom, prenom, email, motDePasse, adresse, telephone);

            case "utilisateur":
                return new Employe(id, nom, prenom, email, motDePasse, adresse, telephone);

            default:
                throw new DatabaseException("Rôle utilisateur inconnu : " + role);
        }
    }

    /**
     * Récupère une adresse par son ID.
     *
     * @param id L'ID de l'adresse.
     * @return Une instance de Adresse, ou null si non trouvée.
     */
    private Adresse getAdresseParId(int id) {
        String query = "SELECT * FROM Adresse WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setInt(1, id);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String rue = resultSet.getString("rue");
                String codePostal = resultSet.getString("codePostal");
                String ville = resultSet.getString("ville");

                return new Adresse(rue, codePostal, ville);
            } else {
                return null;
            }

        } catch (SQLException e) {
            throw new DatabaseException("Erreur lors de la récupération de l'adresse.", e);
        }
    }
}

