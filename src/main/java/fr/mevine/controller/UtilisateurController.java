package fr.mevine.controller;


import fr.mevine.dao.UtilisateurDAO;
import fr.mevine.model.Utilisateur;

public class UtilisateurController {
    private UtilisateurDAO utilisateurDAO;

    public UtilisateurController() {
        utilisateurDAO = new UtilisateurDAO();
    }

    public Utilisateur authentifierUtilisateur(String email, String motDePasse) {
        Utilisateur utilisateur = utilisateurDAO.getUtilisateurParEmail(email);
        if (utilisateur != null && utilisateur.getMotDePasse().equals(motDePasse)) {
            // Authentification réussie
            return utilisateur;
        } else {
            // Échec de l'authentification
            return null;
        }
    }
}
