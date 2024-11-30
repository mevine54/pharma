package fr.mevine.controller;


import fr.mevine.model.Mutuelle;

import java.util.ArrayList;
import java.util.List;

public class MutuelleController {
    private final List<Mutuelle> mutuelles = new ArrayList<>();

    public void ajouterMutuelle(Mutuelle mutuelle) {
        if (mutuelle == null) {
            throw new IllegalArgumentException("La mutuelle ne peut pas être null.");
        }
        mutuelles.add(mutuelle);
    }

    public List<Mutuelle> listerMutuelles() {
        return new ArrayList<>(mutuelles);
    }

    public Mutuelle rechercherMutuelleParNom(String nom) {
        return mutuelles.stream()
                .filter(mutuelle -> mutuelle.getNom().equalsIgnoreCase(nom))
                .findFirst()
                .orElse(null);
    }

    public void supprimerMutuelle(String nom) {
        mutuelles.removeIf(mutuelle -> mutuelle.getNom().equalsIgnoreCase(nom));
    }
}
