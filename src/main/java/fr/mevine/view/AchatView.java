package fr.mevine.view;

import fr.mevine.controller.AchatController;
import fr.mevine.controller.ClientController;
import fr.mevine.controller.MedecinController;
import fr.mevine.model.Client;
import fr.mevine.model.Medecin;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class AchatView extends JPanel {
    private AchatController achatController;
    private MainDashboardView mainDashboardView;
    private Map<String, Double> prixMedicaments;

    public AchatView(ClientController clientController, MedecinController medecinController, AchatController achatController, MainDashboardView mainDashboardView) {
        this.achatController = achatController;
        this.mainDashboardView = mainDashboardView;
        this.prixMedicaments = new HashMap<>();
        initPrixMedicaments();
        initComponents(clientController, medecinController);
    }

    private void initPrixMedicaments() {
        prixMedicaments.put("Paracétamol", 5.00);
        prixMedicaments.put("Ibuprofène", 7.50);
        prixMedicaments.put("Aspirine", 6.00);
    }

    private void initComponents(ClientController clientController, MedecinController medecinController) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        setBackground(new Color(230, 255, 230));

        JLabel labelTitre = new JLabel("Achat direct sélectionné", SwingConstants.CENTER);
        labelTitre.setFont(new Font("Arial", Font.BOLD, 18));
        add(labelTitre, BorderLayout.NORTH);

        JPanel panelFormulaire = new JPanel();
        panelFormulaire.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Label et ComboBox pour le type d'achat
        JLabel labelTypeAchat = new JLabel("Sélectionner le type d'achat:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFormulaire.add(labelTypeAchat, gbc);

        JComboBox<String> typeAchatCombo = new JComboBox<>(new String[]{"Achat direct", "Achat via ordonnance"});
        gbc.gridx = 1;
        gbc.gridy = 0;
        panelFormulaire.add(typeAchatCombo, gbc);

        // Label et ComboBox pour le médicament
        JLabel labelMedicament = new JLabel("Sélectionner un médicament:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFormulaire.add(labelMedicament, gbc);

        JComboBox<String> medicamentCombo = new JComboBox<>(prixMedicaments.keySet().toArray(new String[0]));
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulaire.add(medicamentCombo, gbc);

        // Prix unitaire
        JLabel labelPrixUnitaire = new JLabel("Prix unitaire:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulaire.add(labelPrixUnitaire, gbc);

        JTextField prixUnitaireField = new JTextField();
        prixUnitaireField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 2;
        panelFormulaire.add(prixUnitaireField, gbc);

        // Quantité
        JLabel labelQuantite = new JLabel("Quantité:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFormulaire.add(labelQuantite, gbc);

        JTextField quantiteField = new JTextField("1");
        gbc.gridx = 1;
        gbc.gridy = 3;
        panelFormulaire.add(quantiteField, gbc);

        // Prix total
        JLabel labelPrixTotal = new JLabel("Prix total:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelFormulaire.add(labelPrixTotal, gbc);

        JTextField prixTotalField = new JTextField();
        prixTotalField.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 4;
        panelFormulaire.add(prixTotalField, gbc);

        // Bouton pour ajouter le médicament
        JButton btnAjouterMedicament = new JButton("Ajouter médicament");
        gbc.gridx = 1;
        gbc.gridy = 5;
        panelFormulaire.add(btnAjouterMedicament, gbc);

        // Zone de texte pour les médicaments ajoutés
        JLabel labelMedicamentsAjoutes = new JLabel("Médicaments ajoutés:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelFormulaire.add(labelMedicamentsAjoutes, gbc);

        JTextArea medicamentsAjoutesArea = new JTextArea(5, 20);
        medicamentsAjoutesArea.setEditable(false);
        gbc.gridx = 1;
        gbc.gridy = 6;
        panelFormulaire.add(new JScrollPane(medicamentsAjoutesArea), gbc);

        // Labels et ComboBox pour sélectionner un médecin et un client (affichés uniquement pour l'achat via ordonnance)
        JLabel labelMedecin = new JLabel("Sélectionner un médecin traitant:");
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelFormulaire.add(labelMedecin, gbc);
        JComboBox<String> medecinCombo = new JComboBox<>(medecinController.listerMedecins().stream().map(m -> m.getNom() + " " + m.getPrenom()).toArray(String[]::new));
        gbc.gridx = 1;
        gbc.gridy = 7;
        panelFormulaire.add(medecinCombo, gbc);

        JLabel labelClient = new JLabel("Sélectionner un client:");
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelFormulaire.add(labelClient, gbc);
        JComboBox<String> clientCombo = new JComboBox<>(clientController.listerClients().stream().map(c -> c.getNom() + " " + c.getPrenom()).toArray(String[]::new));
        gbc.gridx = 1;
        gbc.gridy = 8;
        panelFormulaire.add(clientCombo, gbc);

        // Ajout du formulaire au centre
        add(panelFormulaire, BorderLayout.CENTER);

        // Boutons en bas
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnValiderAchat = new JButton("Valider l'achat");
        JButton btnRetour = new JButton("Retour");

        panelBoutons.add(btnValiderAchat);
        panelBoutons.add(btnRetour);
        add(panelBoutons, BorderLayout.SOUTH);

        // Actions des boutons et événements
        btnRetour.addActionListener(e -> mainDashboardView.afficherVue("Accueil"));

        btnValiderAchat.addActionListener(e -> {
            // Logique de validation de l'achat
            String medicament = (String) medicamentCombo.getSelectedItem();
            String quantiteStr = quantiteField.getText();
            try {
                int quantite = Integer.parseInt(quantiteStr);
                double prixUnitaire = prixMedicaments.get(medicament);
                double prixTotal = quantite * prixUnitaire;
                prixTotalField.setText(String.valueOf(prixTotal));

                // Ajouter le médicament à la liste des médicaments ajoutés
                medicamentsAjoutesArea.append(medicament + " - Quantité: " + quantite + " - Prix Total: " + prixTotal + "\n");

                // Enregistrer l'achat via le contrôleur (à implémenter selon votre logique)
                if (typeAchatCombo.getSelectedItem().equals("Achat via ordonnance")) {
                    String medecinNom = (String) medecinCombo.getSelectedItem();
                    String clientNom = (String) clientCombo.getSelectedItem();
                    Medecin medecin = medecinController.getMedecinByName(medecinNom);
                    Client client = clientController.getClientByName(clientNom);
                    achatController.ajouterAchatAvecOrdonnance(medicament, quantiteStr, client, medecin);
                } else {
                    achatController.ajouterAchat(medicament, quantiteStr);
                }
                JOptionPane.showMessageDialog(this, "Achat validé et enregistré !", "Succès", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnAjouterMedicament.addActionListener(e -> {
            // Mettre à jour le prix total dès qu'on ajoute un médicament
            try {
                String medicament = (String) medicamentCombo.getSelectedItem();
                int quantite = Integer.parseInt(quantiteField.getText());
                double prixUnitaire = prixMedicaments.get(medicament);
                double prixTotal = quantite * prixUnitaire;
                prixTotalField.setText(String.valueOf(prixTotal));

                // Ajouter le médicament à la liste des médicaments ajoutés
                medicamentsAjoutesArea.append(medicament + " - Quantité: " + quantite + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Mettre à jour le prix unitaire lorsqu'un médicament est sélectionné
        medicamentCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String medicament = (String) e.getItem();
                prixUnitaireField.setText(String.valueOf(prixMedicaments.get(medicament)));
            }
        });

        // Changer le titre en fonction du type d'achat sélectionné et afficher/masquer les options
        typeAchatCombo.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String typeAchat = (String) e.getItem();
                labelTitre.setText(typeAchat + " sélectionné");
                boolean isOrdonnance = typeAchat.equals("Achat via ordonnance");
                labelMedecin.setVisible(isOrdonnance);
                medecinCombo.setVisible(isOrdonnance);
                labelClient.setVisible(isOrdonnance);
                clientCombo.setVisible(isOrdonnance);
            }
        });
    }
}
