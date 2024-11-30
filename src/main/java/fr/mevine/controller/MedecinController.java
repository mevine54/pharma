package fr.mevine.controller;

import fr.mevine.model.Medecin;
import fr.mevine.model.Adresse;
import java.util.ArrayList;
import java.util.List;

public class MedecinController {
    private List<Medecin> medecins = new ArrayList<>();

    public void ajouterMedecin(Medecin medecin) {
        medecins.add(medecin);
    }

    public List<Medecin> listerMedecins() {
        return medecins;
    }

    public Medecin getMedecinByName(String fullName) {
        for (Medecin medecin : medecins) {
            String medecinFullName = medecin.getNom() + " " + medecin.getPrenom();
            if (medecinFullName.equalsIgnoreCase(fullName)) {
                return medecin;
            }
        }
        return null;
    }

    public void modifierMedecin(Medecin medecin) {
        // Si vous utilisez une base de données, effectuez la mise à jour ici
        // Sinon, les modifications sont déjà effectuées sur l'objet medecin
    }

    public void supprimerMedecin(Medecin medecin) {
        medecins.remove(medecin);
        // Si vous utilisez une base de données, effectuez la suppression ici
    }
}
