package fr.mevine.view;

import fr.mevine.controller.ClientController;
import fr.mevine.model.Client;
import fr.mevine.model.Adresse;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ClientView extends JPanel {
    private ClientController clientController;
    private MainDashboardView mainDashboardView;

    public ClientView(ClientController clientController, MainDashboardView mainDashboardView) {
        this.clientController = clientController;
        this.mainDashboardView = mainDashboardView;
        initComponents();
    }

    private void initComponents() {
        // Configuration du layout
        setLayout(new BorderLayout());

        // Panneau pour gérer les clients
        JPanel panelClient = new JPanel();
        panelClient.setLayout(new BoxLayout(panelClient, BoxLayout.Y_AXIS));
        panelClient.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelClient.setBackground(new Color(230, 255, 230)); // Couleur de fond

        // Label pour le titre
        JLabel labelClient = new JLabel("Gestion des clients");
        labelClient.setFont(new Font("Arial", Font.BOLD, 18));

        // Liste déroulante pour sélectionner un client
        JLabel labelSelectClient = new JLabel("Sélectionner un client:");
        JComboBox<String> clientCombo = new JComboBox<>(getListeClients());

        // Détails du client (affichage)
        JTextArea detailsClientArea = new JTextArea(5, 30);
        detailsClientArea.setEditable(false);
        detailsClientArea.setLineWrap(true);
        detailsClientArea.setWrapStyleWord(true);

        // Afficher les informations du client sélectionné
        clientCombo.addActionListener(e -> {
            String clientSelectionne = (String) clientCombo.getSelectedItem();
            Client client = clientController.getClientByName(clientSelectionne);
            if (client != null) {
                detailsClientArea.setText("Détails du client: " + clientSelectionne
                        + "\nAdresse: " + client.getAdresse()
                        + "\nTéléphone: " + client.getTelephone()
                        + "\nEmail: " + client.getEmail());
            } else {
                detailsClientArea.setText("Client non trouvé.");
            }
        });

        // Bouton pour créer un nouveau client
        JButton btnNouveauClient = new JButton("Créer un nouveau client");
        btnNouveauClient.addActionListener(e -> {
            JPanel nouveauClientPanel = new JPanel(new GridLayout(6, 2));
            nouveauClientPanel.add(new JLabel("Nom:"));
            JTextField nomField = new JTextField();
            nouveauClientPanel.add(nomField);
            nouveauClientPanel.add(new JLabel("Prénom:"));
            JTextField prenomField = new JTextField();
            nouveauClientPanel.add(prenomField);
            nouveauClientPanel.add(new JLabel("Adresse:"));
            JTextField adresseField = new JTextField();
            nouveauClientPanel.add(adresseField);
            nouveauClientPanel.add(new JLabel("Téléphone:"));
            JTextField telField = new JTextField();
            nouveauClientPanel.add(telField);
            nouveauClientPanel.add(new JLabel("Email:"));
            JTextField emailField = new JTextField();
            nouveauClientPanel.add(emailField);

            int result = JOptionPane.showConfirmDialog(this, nouveauClientPanel, "Créer un nouveau client", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                // Création du nouveau client
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

                // Création de l'objet Client
                Adresse adresse = new Adresse(adresseStr, "", ""); // Adapter selon votre classe Adresse
                Client nouveauClient = new Client(0, nom, prenom, email, "", adresse, telephone, "", null, null);

                // Ajout du client via le contrôleur
                clientController.ajouterClient(nouveauClient);

                // Mise à jour de la liste déroulante
                clientCombo.addItem(nom + " " + prenom);
                JOptionPane.showMessageDialog(this, "Nouveau client créé !");
            }
        });

        // Bouton pour modifier un client
        JButton btnModifierClient = new JButton("Modifier le client sélectionné");
        btnModifierClient.addActionListener(e -> {
            String clientSelectionne = (String) clientCombo.getSelectedItem();
            Client client = clientController.getClientByName(clientSelectionne);
            if (client != null) {
                JPanel modificationClientPanel = new JPanel(new GridLayout(6, 2));
                modificationClientPanel.add(new JLabel("Nom:"));
                JTextField nomField = new JTextField(client.getNom());
                modificationClientPanel.add(nomField);
                modificationClientPanel.add(new JLabel("Prénom:"));
                JTextField prenomField = new JTextField(client.getPrenom());
                modificationClientPanel.add(prenomField);
                modificationClientPanel.add(new JLabel("Adresse:"));
                JTextField adresseField = new JTextField(client.getAdresse().toString());
                modificationClientPanel.add(adresseField);
                modificationClientPanel.add(new JLabel("Téléphone:"));
                JTextField telField = new JTextField(client.getTelephone());
                modificationClientPanel.add(telField);
                modificationClientPanel.add(new JLabel("Email:"));
                JTextField emailField = new JTextField(client.getEmail());
                modificationClientPanel.add(emailField);

                int result = JOptionPane.showConfirmDialog(this, modificationClientPanel, "Modifier le client", JOptionPane.OK_CANCEL_OPTION);
                if (result == JOptionPane.OK_OPTION) {
                    // Mise à jour du client
                    client.setNom(nomField.getText().trim());
                    client.setPrenom(prenomField.getText().trim());
                    client.setAdresse(new Adresse(adresseField.getText().trim(), "", "")); // Adapter selon votre classe Adresse
                    client.setTelephone(telField.getText().trim());
                    client.setEmail(emailField.getText().trim());

                    // Mise à jour via le contrôleur
                    clientController.modifierClient(client);

                    // Mise à jour de la liste déroulante
                    clientCombo.removeItem(clientSelectionne);
                    clientCombo.addItem(client.getNom() + " " + client.getPrenom());
                    detailsClientArea.setText("Détails du client: " + client.getNom() + " " + client.getPrenom()
                            + "\nAdresse: " + client.getAdresse()
                            + "\nTéléphone: " + client.getTelephone()
                            + "\nEmail: " + client.getEmail());
                    JOptionPane.showMessageDialog(this, "Client modifié !");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Client non trouvé !");
            }
        });

        // Bouton pour supprimer un client
        JButton btnSupprimerClient = new JButton("Supprimer le client sélectionné");
        btnSupprimerClient.addActionListener(e -> {
            String clientSelectionne = (String) clientCombo.getSelectedItem();
            int confirmation = JOptionPane.showConfirmDialog(this, "Êtes-vous sûr de vouloir supprimer ce client ?", "Supprimer le client", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                Client client = clientController.getClientByName(clientSelectionne);
                if (client != null) {
                    clientController.supprimerClient(client);
                    clientCombo.removeItem(clientSelectionne);
                    detailsClientArea.setText("");
                    JOptionPane.showMessageDialog(this, "Client supprimé !");
                } else {
                    JOptionPane.showMessageDialog(this, "Client non trouvé !");
                }
            }
        });

        // Panneau pour aligner les boutons
        JPanel boutonsPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        boutonsPanel.add(btnModifierClient);
        boutonsPanel.add(btnSupprimerClient);
        boutonsPanel.add(btnNouveauClient);

        // Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            if (mainDashboardView != null) {
                mainDashboardView.afficherVue("Accueil");
            }
        });

        JButton btnQuitter = new JButton("Quitter");
        btnQuitter.addActionListener(e -> System.exit(0));

        // Ajout des composants au panneau client
        panelClient.add(labelClient);
        panelClient.add(Box.createRigidArea(new Dimension(0, 10)));
        panelClient.add(labelSelectClient);
        panelClient.add(clientCombo);
        panelClient.add(Box.createRigidArea(new Dimension(0, 10)));
        panelClient.add(new JScrollPane(detailsClientArea));
        panelClient.add(Box.createRigidArea(new Dimension(0, 10)));
        panelClient.add(boutonsPanel);
        panelClient.add(Box.createRigidArea(new Dimension(0, 10)));
        panelClient.add(btnRetour);
        panelClient.add(btnQuitter);

        // Ajout du panneau client à ce JPanel
        add(panelClient, BorderLayout.CENTER);
    }

    // Méthode pour obtenir la liste des clients
    private String[] getListeClients() {
        List<Client> clients = clientController.listerClients();
        return clients.stream()
                .map(client -> client.getNom() + " " + client.getPrenom())
                .toArray(String[]::new);
    }
}
