package fr.mevine.controller;


import fr.mevine.model.Medicament;

import java.util.ArrayList;
import java.util.List;

public class MedicamentController {
    private final List<Medicament> medicaments = new ArrayList<>();

    public void ajouterMedicament(Medicament medicament) {
        if (medicament == null) {
            throw new IllegalArgumentException("Le médicament ne peut pas être null.");
        }
        medicaments.add(medicament);
    }

    public List<Medicament> listerMedicaments() {
        return new ArrayList<>(medicaments);
    }

    public Medicament rechercherMedicamentParNom(String nom) {
        return medicaments.stream()
                .filter(medicament -> medicament.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    public void supprimerMedicament(String nom) {
        medicaments.removeIf(medicament -> medicament.getNom().equalsIgnoreCase(nom));
    }
}
