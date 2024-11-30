package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;

public class OrdonnanceMedicament {
    private int id;
    private Ordonnance ordonnance;
    private Medicament medicament;
    private int quantitePrescrite;
    private double prixTotal;

    public OrdonnanceMedicament(int id, Ordonnance ordonnance, Medicament medicament, int quantitePrescrite) {
        setId(id);
        setOrdonnance(ordonnance);
        setMedicament(medicament);
        setQuantitePrescrite(quantitePrescrite);
        calculerPrixTotal();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidInputException("L'ID doit être un entier positif.");
        }
        this.id = id;
    }

    public Ordonnance getOrdonnance() {
        return ordonnance;
    }

    public void setOrdonnance(Ordonnance ordonnance) {
        if (ordonnance == null) {
            throw new InvalidInputException("L'ordonnance ne peut pas être null.");
        }
        this.ordonnance = ordonnance;
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

    public int getQuantitePrescrite() {
        return quantitePrescrite;
    }

    public void setQuantitePrescrite(int quantitePrescrite) {
        if (quantitePrescrite <= 0) {
            throw new InvalidInputException("La quantité prescrite doit être supérieure à zéro.");
        }
        this.quantitePrescrite = quantitePrescrite;
        calculerPrixTotal();
    }

    public double getPrixTotal() {
        return prixTotal;
    }

    private void calculerPrixTotal() {
        if (medicament != null) {
            this.prixTotal = medicament.getPrix() * quantitePrescrite;
        }
    }
}
