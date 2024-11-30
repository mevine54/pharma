package fr.mevine.view;

import fr.mevine.controller.AchatController;
import fr.mevine.controller.ClientController;
import fr.mevine.controller.MedecinController;
import javax.swing.*;
import java.awt.*;

public class AchatView extends JPanel {
    private AchatController achatController;
    private MainDashboardView mainDashboardView;

    public AchatView(ClientController clientController, MedecinController medecinController, AchatController achatController, MainDashboardView mainDashboardView) {
        this.achatController = achatController;
        this.mainDashboardView = mainDashboardView;
        initComponents(clientController, medecinController);
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

        JComboBox<String> medicamentCombo = new JComboBox<>(new String[]{"Paracétamol", "Ibuprofène", "Aspirine"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        panelFormulaire.add(medicamentCombo, gbc);

        // Prix unitaire
        JLabel labelPrixUnitaire = new JLabel("Prix unitaire:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFormulaire.add(labelPrixUnitaire, gbc);

        JTextField prixUnitaireField = new JTextField("5.00");
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

        JTextField prixTotalField = new JTextField("5.00");
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

        // Ajout du formulaire au centre
        add(panelFormulaire, BorderLayout.CENTER);

        // Boutons en bas
        JPanel panelBoutons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnValiderAchat = new JButton("Valider l'achat");
        JButton btnRetour = new JButton("Retour");

        panelBoutons.add(btnValiderAchat);
        panelBoutons.add(btnRetour);
        add(panelBoutons, BorderLayout.SOUTH);

        // Actions des boutons
        btnRetour.addActionListener(e -> mainDashboardView.afficherVue("Accueil"));
        btnValiderAchat.addActionListener(e -> {
            // Logique de validation de l'achat
            String medicament = (String) medicamentCombo.getSelectedItem();
            String quantiteStr = quantiteField.getText();
            try {
                int quantite = Integer.parseInt(quantiteStr);
                double prixUnitaire = Double.parseDouble(prixUnitaireField.getText());
                double prixTotal = quantite * prixUnitaire;
                prixTotalField.setText(String.valueOf(prixTotal));

                // Ajouter le médicament à la liste des médicaments ajoutés
                medicamentsAjoutesArea.append(medicament + " - Quantité: " + quantite + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Veuillez entrer une quantité valide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
