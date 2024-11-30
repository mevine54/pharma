package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;

public class Medecin extends Utilisateur {
    private String numeroAgrement;

    public Medecin(int id, String nom, String prenom, String email, String motDePasse, Adresse adresse, String telephone,
                   String numeroAgrement) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone);
        setNumeroAgrement(numeroAgrement);
    }

    public String getNumeroAgrement() {
        return numeroAgrement;
    }

    public void setNumeroAgrement(String numeroAgrement) {
        if (numeroAgrement == null || numeroAgrement.isEmpty()) {
            throw new InvalidInputException("Le numéro d'agrément ne peut pas être vide.");
        }
        if (!numeroAgrement.matches("[A-Z0-9]{10}")) {
            throw new InvalidInputException("Numéro d'agrément invalide. Il doit contenir 10 caractères alphanumériques.");
        }
        this.numeroAgrement = numeroAgrement;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numéro d'agrément: " + numeroAgrement;
    }
}

