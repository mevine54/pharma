package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;

public class Mutuelle {
    private String nom;
    private Adresse adresse;
    private double tauxRemboursement;

    public Mutuelle(String nom, Adresse adresse, double tauxRemboursement) {
        setNom(nom);
        setAdresse(adresse);
        setTauxRemboursement(tauxRemboursement);
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.isEmpty()) {
            throw new InvalidInputException("Le nom de la mutuelle ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        if (adresse == null) {
            throw new InvalidInputException("L'adresse de la mutuelle ne peut pas être null.");
        }
        this.adresse = adresse;
    }

    public double getTauxRemboursement() {
        return tauxRemboursement;
    }

    public void setTauxRemboursement(double tauxRemboursement) {
        if (tauxRemboursement < 0 || tauxRemboursement > 100) {
            throw new InvalidInputException("Le taux de remboursement doit être entre 0 et 100.");
        }
        this.tauxRemboursement = tauxRemboursement;
    }
}
