package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;
import fr.mevine.util.Validator;

/**
 * Classe abstraite représentant un utilisateur du système.
 */
public abstract class Utilisateur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
    private Adresse adresse;
    private String telephone;

    /**
     * Constructeur de la classe Utilisateur.
     *
     * @param id          L'identifiant unique de l'utilisateur.
     * @param nom         Le nom de l'utilisateur.
     * @param prenom      Le prénom de l'utilisateur.
     * @param email       L'email de l'utilisateur.
     * @param motDePasse  Le mot de passe de l'utilisateur.
     * @param adresse     L'adresse de l'utilisateur.
     * @param telephone   Le téléphone de l'utilisateur.
     */
    public Utilisateur(int id, String nom, String prenom, String email, String motDePasse, Adresse adresse, String telephone) {
        setId(id);
        setNom(nom);
        setPrenom(prenom);
        setEmail(email);
        setMotDePasse(motDePasse);
        setAdresse(adresse);
        setTelephone(telephone);
    }

    // Getters et setters avec validations

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidInputException("L'ID doit être un entier positif.");
        }
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.trim().isEmpty()) {
            throw new InvalidInputException("Le nom ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        if (prenom == null || prenom.trim().isEmpty()) {
            throw new InvalidInputException("Le prénom ne peut pas être vide.");
        }
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (!Validator.isValidEmail(email)) {
            throw new InvalidInputException("Format d'email invalide.");
        }
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        if (!Validator.isValidMotDePasse(motDePasse)) {
            throw new InvalidInputException("Le mot de passe doit contenir au moins 8 caractères.");
        }
        this.motDePasse = motDePasse;
    }

    public Adresse getAdresse() {
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        if (adresse == null) {
            throw new InvalidInputException("L'adresse ne peut pas être null.");
        }
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        if (adresse == null) {
            throw new InvalidInputException("L'adresse ne peut pas être null.");
        }
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return nom + " " + prenom + ", " + adresse.toString() + ", Tel: " + telephone + ", Email: " + email;
    }

}
