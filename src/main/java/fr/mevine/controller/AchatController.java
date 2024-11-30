package fr.mevine.controller;

import fr.mevine.model.Achat;
import fr.mevine.enums.CategorieMedicament;
import fr.mevine.model.Client;
import fr.mevine.model.Medicament;
import fr.mevine.util.Validator;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AchatController {
    private List<Achat> achatsValides;

    public AchatController() {
        achatsValides = new ArrayList<>();
    }

    public List<Medicament> getListeMedicaments() {
        // Pour l'exemple, nous allons créer une liste statique
        List<Medicament> medicaments = new ArrayList<>();
        medicaments.add(new Medicament(
                1,
                "Paracétamol",
                CategorieMedicament.ANTALGIQUE,
                5.00,
                LocalDate.of(2020, 1, 1),
                100
        ));
        medicaments.add(new Medicament(
                2,
                "Ibuprofène",
                CategorieMedicament.ANTALGIQUE,
                7.50,
                LocalDate.of(2019, 6, 15),
                50
        ));
        medicaments.add(new Medicament(
                3,
                "Aspirine",
                CategorieMedicament.ANTALGIQUE,
                6.00,
                LocalDate.of(2018, 3, 20),
                75
        ));
        return medicaments;
    }

    public boolean ajouterAchat(String nomMedicament, String quantiteStr) {
        try {
            // Validation des données
            if (!Validator.isValidNumber(quantiteStr)) {
                throw new NumberFormatException("Quantité invalide.");
            }
            int quantite = Integer.parseInt(quantiteStr);
            if (quantite <= 0) {
                throw new NumberFormatException("La quantité doit être positive.");
            }

            Medicament medicament = trouverMedicamentParNom(nomMedicament);
            if (medicament == null) {
                throw new IllegalArgumentException("Médicament non trouvé.");
            }

            // Vérifier que la quantité demandée est disponible
            if (quantite > medicament.getQuantite()) {
                throw new IllegalArgumentException("Quantité demandée supérieure au stock disponible.");
            }

            // Créer l'achat avec les paramètres requis
            int achatId = 0; // ID par défaut si auto-généré
            LocalDate dateAchat = LocalDate.now();
            Client client = null; // Client inconnu pour achat direct

            Achat achat = new Achat(achatId, dateAchat, client, medicament, quantite);
            achatsValides.add(achat);

            // Mettre à jour le stock du médicament
            medicament.setQuantite(medicament.getQuantite() - quantite);

            return true;
        } catch (IllegalArgumentException e) {
            // Gérer l'exception au niveau de la vue
            throw e;
        }
    }

    private Medicament trouverMedicamentParNom(String nom) {
        for (Medicament medicament : getListeMedicaments()) {
            if (medicament.getNom().equalsIgnoreCase(nom)) {
                return medicament;
            }
        }
        return null;
    }

    public List<Achat> getAchatsValides() {
        return achatsValides;
    }

    public void validerAchats() {
        // Logique pour valider les achats (enregistrer en base de données, mettre à jour les stocks, etc.)
        // Pour l'exemple, nous allons simplement vider la liste
        achatsValides.clear();
    }
}
