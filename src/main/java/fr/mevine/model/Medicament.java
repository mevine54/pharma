package fr.mevine.model;

import fr.mevine.enums.CategorieMedicament;
import fr.mevine.exceptions.InvalidInputException;

import java.time.LocalDate;

/**
 * Classe représentant un médicament.
 */
public class Medicament {
    private int id;
    private String nom;
    private CategorieMedicament categorie;
    private double prix;
    private LocalDate dateMiseEnService;
    private int quantite;

    /**
     * Constructeur complet.
     *
     * @param id                L'identifiant unique du médicament.
     * @param nom               Le nom du médicament.
     * @param categorie         La catégorie du médicament.
     * @param prix              Le prix du médicament.
     * @param dateMiseEnService La date de mise en service du médicament.
     * @param quantite          La quantité disponible du médicament.
     */
    public Medicament(int id, String nom, CategorieMedicament categorie, double prix, LocalDate dateMiseEnService, int quantite) {
        setId(id);
        setNom(nom);
        setCategorie(categorie);
        setPrix(prix);
        setDateMiseEnService(dateMiseEnService);
        setQuantite(quantite);
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
            throw new InvalidInputException("Le nom du médicament ne peut pas être vide.");
        }
        this.nom = nom;
    }

    public CategorieMedicament getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieMedicament categorie) {
        if (categorie == null) {
            throw new InvalidInputException("La catégorie du médicament ne peut pas être null.");
        }
        this.categorie = categorie;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        if (prix < 0) {
            throw new InvalidInputException("Le prix du médicament ne peut pas être négatif.");
        }
        this.prix = prix;
    }

    public LocalDate getDateMiseEnService() {
        return dateMiseEnService;
    }

    public void setDateMiseEnService(LocalDate dateMiseEnService) {
        if (dateMiseEnService == null || dateMiseEnService.isAfter(LocalDate.now())) {
            throw new InvalidInputException("La date de mise en service ne peut pas être dans le futur ou null.");
        }
        this.dateMiseEnService = dateMiseEnService;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        if (quantite < 0) {
            throw new InvalidInputException("La quantité ne peut pas être négative.");
        }
        this.quantite = quantite;
    }
}
