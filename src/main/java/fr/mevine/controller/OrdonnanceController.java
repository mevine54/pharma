package fr.mevine.controller;


import fr.mevine.model.Ordonnance;

import java.util.ArrayList;
import java.util.List;

public class OrdonnanceController {
    private final List<Ordonnance> ordonnances = new ArrayList<>();

    public void ajouterOrdonnance(Ordonnance ordonnance) {
        if (ordonnance == null) {
            throw new IllegalArgumentException("L'ordonnance ne peut pas être null.");
        }
        ordonnances.add(ordonnance);
    }

    public List<Ordonnance> listerOrdonnances() {
        return new ArrayList<>(ordonnances);
    }

    public Ordonnance rechercherOrdonnanceParId(int id) {
        return ordonnances.stream()
                .filter(ordonnance -> ordonnance.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void supprimerOrdonnance(int id) {
        ordonnances.removeIf(ordonnance -> ordonnance.getId() == id);
    }
}
