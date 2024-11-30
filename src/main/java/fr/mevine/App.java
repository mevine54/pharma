package fr.mevine;

import fr.mevine.model.Adresse;
import fr.mevine.model.Manager;
import fr.mevine.view.MainDashboardView;

public class App {
    public static void main(String[] args) {
        // Créer une adresse fictive
        Adresse adresse = new Adresse("123 Rue de la Paix", "75000", "Paris");

        // Créer un manager fictif pour le test
        Manager manager = new Manager(
                1,                        // ID du manager
                "Dupont",                 // Nom
                "Jean",                   // Prénom
                "jean.dupont@example.com",// Email
                "motdepasse123",          // Mot de passe
                adresse,                  // Adresse
              "telephone"
        );

        // Créer et afficher la vue principale
        MainDashboardView dashboard = new MainDashboardView();
        dashboard.setVisible(true);
    }
}

