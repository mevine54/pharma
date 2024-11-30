package fr.mevine.view;

import fr.mevine.controller.MedecinController;
import fr.mevine.model.Medecin;
import fr.mevine.model.Adresse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class MedecinView extends JPanel {
    private MedecinController medecinController;
    private MainDashboardView mainDashboardView;

    public MedecinView(MedecinController medecinController, MainDashboardView mainDashboardView) {
        this.medecinController = medecinController;
        this.mainDashboardView = mainDashboardView;
        initComponents();
    }

    private void initComponents() {
        // Configuration du layout
        setLayout(new BorderLayout());

        // Panneau pour gérer les médecins
        JPanel panelMedecin = new JPanel();
        panelMedecin.setLayout(new BoxLayout(panelMedecin, BoxLayout.Y_AXIS));
        panelMedecin.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelMedecin.setBackground(new Color(230, 255, 230)); // Couleur de fond

        // Label pour le titre
        JLabel labelMedecin = new JLabel("Gestion des médecins / spécialistes");
        labelMedecin.setFont(new Font("Arial", Font.BOLD, 18));

        // Liste déroulante pour sélectionner un médecin
        JLabel labelSelectMedecin = new JLabel("Sélectionner un médecin:");
        JComboBox<String> medecinCombo = new JComboBox<>(getListeMedecins());

        // Détails du médecin (affichage)
        JTextArea detailsMedecinArea = new JTextArea(5, 30);
        detailsMedecinArea.setEditable(false);
        detailsMedecinArea.setLineWrap(true);
        detailsMedecinArea.setWrapStyleWord(true);

        // Afficher les informations du médecin sélectionné
        medecinCombo.addActionListener(e -> {
            String medecinSelectionne = (String) medecinCombo.getSelectedItem();
            Medecin medecin = medecinController.getMedecinByName(medecinSelectionne);
            if (medecin != null) {
                detailsMedecinArea.setText("Détails du médecin: " + medecinSelectionne
                        + "\nAdresse: " + medecin.getAdresse()
                        + "\nTéléphone: " + medecin.getTelephone()
                        + "\nEmail: " + medecin.getEmail());
            } else {
                detailsMedecinArea.setText("Médecin non trouvé.");
            }
        });

        // Bouton pour créer un nouveau médecin
        JButton btnNouveauMedecin = new JButton("Créer un nouveau médecin");
        btnNouveauMedecin.addActionListener(e -> {
            JPanel nouveauMedecinPanel = new JPanel(new GridLayout(6, 2));
            nouveauMedecinPanel.add(new JLabel("Nom:"));
            JTextField nomField = new JTextField();
            nouveauMedecinPanel.add(nomField);
            nouveauMedecinPanel.add(new JLabel("Prénom:"));
            JTextField prenomField = new JTextField();
            nouveauMedecinPanel.add(prenomField);
            nouveauMedecinPanel.add(new JLabel("Adresse:"));
            JTextField adresseField = new JTextField();
            nouveauMedecinPanel.add(adresseField);
            nouveauMedecinPanel.add(new JLabel("Téléphone:"));
            JTextField telField = new JTextField();
            nouveauMedecinPanel.add(telField);
            nouveauMedecinPanel.add(new JLabel("Email:"));
            JTextField emailField = new JTextField();
            nouveauMedecinPanel.add(emailField);

            int result = JOptionPane.showConfirmDialog(this, nouveauMedecinPanel, "Créer un nouveau médecin", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Création du nouveau médecin
                String nom = nomField.getText().trim();
                String prenom = prenomField.getText().trim();
                String adresseStr = adresseField.getText().trim();
                String telephone = telField.getText().trim();
                String email = emailField.getText().trim();

                // Validation des données (à implémenter selon vos besoins)

                if (nom.isEmpty() || prenom.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Le nom et le prénom sont obligatoires.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Création de l'objet Medecin
                Adresse adresse = new Adresse(adresseStr, "", ""); // Adapter selon votre classe Adresse
                Medecin nouveauMedecin = new Medecin(0, nom, prenom, email, "", adresse, telephone, "");

                // Ajout du médecin via le contrôleur
                medecinController.ajouterMedecin(nouveauMedecin);

                // Mise à jour de la liste déroulante
                medecinCombo.addItem(nom + " " + prenom);
                JOptionPane.showMessageDialog(this, "Nouveau médecin créé !");
            }
        });

        // Bouton pour modifier un médecin
        JButton btnModifierMedecin = new JButton("Modifier le médecin sélectionné");
        btnModifierMedecin.addActionListener(e -> {
            String medecinSelectionne = (String) medecinCombo.getSelectedItem();
            Medecin medecin = medecinController.getMedecinByName(medecinSelectionne);
            if (medecin != null) {
                JPanel modificationMedecinPanel = new JPanel(new GridLayout(6, 2));
                modificationMedecinPanel.add(new JLabel("Nom:"));
                JTextField nomField = new JTextField(medecin.getNom());
                modificationMedecinPanel.add(nomField);
                modificationMedecinPanel.add(new JLabel("Prénom:"));
                JTextField prenomField = new JTextField(medecin.getPrenom());
                modificationMedecinPanel.add(prenomField);
                modificationMedecinPanel.add(new JLabel("Adresse:"));
                JTextField adresseField = new JTextField(medecin.getAdresse().toString());
                modificationMedecinPanel.add(adresseField);
                modificationMedecinPanel.add(new JLabel("Téléphone:"));
                JTextField telField = new JTextField(medecin.getTelephone());
                modificationMedecinPanel.add(telField);
                modificationMedecinPanel.add(new JLabel("Email:"));
                JTextField emailField = new JTextField(medecin.getEmail());
                modificationMedecinPanel.add(emailField);

                int result = JOptionPane.showConfirmDialog(this, modificationMedecinPanel, "Modifier le médecin", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Mise à jour du médecin
                    medecin.setNom(nomField.getText().trim());
                    medecin.setPrenom(prenomField.getText().trim());
                    medecin.setAdresse(new Adresse(adresseField.getText().trim(), "", "")); // Adapter selon votre classe Adresse
                    medecin.setTelephone(telField.getText().trim());
                    medecin.setEmail(emailField.getText().trim());

                    // Mise à jour via le contrôleur
                    medecinController.modifierMedecin(medecin);

                    // Mise à jour de la liste déroulante
                    medecinCombo.removeItem(medecinSelectionne);
                    medecinCombo.addItem(medecin.getNom() + " " + medecin.getPrenom());
                    detailsMedecinArea.setText("Détails du médecin: " + medecin.getNom() + " " + medecin.getPrenom()
                            + "\nAdresse: " + medecin.getAdresse()
                            + "\nTéléphone: " + medecin.getTelephone()
                            + "\nEmail: " + medecin.getEmail());
                    JOptionPane.showMessageDialog(this, "Médecin modifié !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Médecin non trouvé !");
            }
        });

        // Bouton pour supprimer un médecin
        JButton btnSupprimerMedecin = new JButton("Supprimer le médecin sélectionné");
        btnSupprimerMedecin.addActionListener(e -> {
            String medecinSelectionne = (String) medecinCombo.getSelectedItem();
            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce médecin ?", "Supprimer le médecin", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Medecin medecin = medecinController.getMedecinByName(medecinSelectionne);
                if (medecin != null) {
                    medecinController.supprimerMedecin(medecin);
                    medecinCombo.removeItem(medecinSelectionne);
                    detailsMedecinArea.setText("");
                    JOptionPane.showMessageDialog(this, "Médecin supprimé !");
                } else {
                    JOptionPane.showMessageDialog(this, "Médecin non trouvé !");
                }
            }
        });

        // Panneau pour aligner les boutons
        JPanel boutonsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        boutonsPanel.add(btnModifierMedecin);
        boutonsPanel.add(btnSupprimerMedecin);
        boutonsPanel.add(btnNouveauMedecin);

        // Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            if (mainDashboardView != null) {
                mainDashboardView.afficherVue("Accueil");
            }
        });

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajout des composants au panneau médecin
        panelMedecin.add(labelMedecin);
        panelMedecin.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMedecin.add(labelSelectMedecin);
        panelMedecin.add(medecinCombo);
        panelMedecin.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMedecin.add(new JScrollPane(detailsMedecinArea));
        panelMedecin.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMedecin.add(boutonsPanel);
        panelMedecin.add(Box.createRigidArea(new Dimension(0, 10)));
        panelMedecin.add(btnRetour);
        panelMedecin.add(btnQuitter);

        // Ajout du panneau médecin à ce JPanel
        add(panelMedecin, BorderLayout.CENTER);
    }

    // Méthode pour obtenir la liste des médecins
    private String[] getListeMedecins() {
        List<Medecin> medecins = medecinController.listerMedecins();
        return medecins.stream()
                .map(medecin -> medecin.getNom() + " " + medecin.getPrenom())
                .toArray(String[]::new);
    }
}
