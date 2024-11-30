package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;

import java.time.LocalDate;

/**
 * Classe représentant un achat.
 */
public class Achat {
    private int id;
    private LocalDate dateAchat;
    private Client client;
    private Medicament medicament;
    private int quantiteAchat;
    private double montantTotal;

    /**
     * Constructeur complet.
     *
     * @param id             L'identifiant unique de l'achat.
     * @param dateAchat      La date de l'achat.
     * @param client         Le client ayant effectué l'achat.
     * @param medicament     Le médicament acheté.
     * @param quantiteAchat  La quantité achetée.
     */
    public Achat(int id, LocalDate dateAchat, Client client, Medicament medicament, int quantiteAchat) {
        setId(id);
        setDateAchat(dateAchat);
        setClient(client);
        setMedicament(medicament);
        setQuantiteAchat(quantiteAchat);
        calculerMontantTotal();
    }

    // Getters et setters avec validations

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidInputException("L'ID de l'achat doit être un entier positif.");
        }
        this.id = id;
    }

    public LocalDate getDateAchat() {
        return dateAchat;
    }

    public void setDateAchat(LocalDate dateAchat) {
        if (dateAchat == null || dateAchat.isAfter(LocalDate.now())) {
            throw new InvalidInputException("La date d'achat ne peut pas être dans le futur ou null.");
        }
        this.dateAchat = dateAchat;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new InvalidInputException("Le client ne peut pas être null.");
        }
        this.client = client;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        if (medicament == null) {
            throw new InvalidInputException("Le médicament ne peut pas être null.");
        }
        this.medicament = medicament;
    }

    public int getQuantiteAchat() {
        return quantiteAchat;
    }

    public void setQuantiteAchat(int quantiteAchat) {
        if (quantiteAchat <= 0) {
            throw new InvalidInputException("La quantité achetée doit être supérieure à zéro.");
        }
        this.quantiteAchat = quantiteAchat;
    }

    public double getMontantTotal() {
        return montantTotal;
    }

    // Méthode pour calculer le montant total
    private void calculerMontantTotal() {
        if (medicament != null && quantiteAchat > 0) {
            this.montantTotal = medicament.getPrix() * quantiteAchat;
        } else {
            this.montantTotal = 0.0;
        }
    }
}
