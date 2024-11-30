package fr.mevine.view;

import fr.mevine.controller.ClientController;
import fr.mevine.controller.MedecinController;
import fr.mevine.controller.AchatController;
import javax.swing.*;
import java.awt.*;

public class MainDashboardView extends JFrame {
    private JPanel panelCentral;
    private ClientController clientController;
    private MedecinController medecinController;
    private AchatController achatController;

    public MainDashboardView() {
        clientController = new ClientController();
        medecinController = new MedecinController();
        achatController = new AchatController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Pharmacie Sparadrap - Tableau de Bord");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());

        // Initialisation de la sidebar
        JPanel sidebar = createSidebar();
        add(sidebar, BorderLayout.WEST);

        // Initialisation du panneau central avec CardLayout
        panelCentral = new JPanel(new CardLayout());
        add(panelCentral, BorderLayout.CENTER);

        // Ajouter les différentes vues au CardLayout
        // Ajouter les différentes vues au CardLayout
        panelCentral.add(new AccueilView(), "Accueil");
        panelCentral.add(new MedecinView(medecinController, this), "Medecin");
        panelCentral.add(new ClientView(clientController, this), "Client");
        panelCentral.add(new HistoriqueAchatsView(clientController, medecinController, this), "HistoriqueAchats");
        panelCentral.add(new AchatView(clientController, medecinController, achatController, this), "Achat");

// Affichage initial de la page d'accueil
        afficherVue("Accueil");
    }

    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(85, 170, 85)); // Couleur verte
        sidebar.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Création des boutons de navigation
        JButton btnAchat = createSidebarButton("Effectuer un achat");
        JButton btnHistorique = createSidebarButton("Consulter l'historique des achats");
        JButton btnMedecin = createSidebarButton("Consulter un médecin");
        JButton btnClient = createSidebarButton("Consulter un client");
        JButton btnAccueil = createSidebarButton("Retour à l'accueil");
        JButton btnQuitter = createSidebarButton("Quitter l'application");

        // Ajout des boutons à la sidebar
        sidebar.add(btnAccueil);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnAchat);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnHistorique);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnMedecin);
        sidebar.add(Box.createRigidArea(new Dimension(0, 10)));
        sidebar.add(btnClient);
        sidebar.add(Box.createVerticalGlue());
        sidebar.add(btnQuitter);

        // Actions des boutons
        btnAccueil.addActionListener(e -> afficherVue("Accueil"));
        btnAchat.addActionListener(e -> afficherVue("Achat"));
        btnHistorique.addActionListener(e -> afficherVue("HistoriqueAchats"));
        btnMedecin.addActionListener(e -> afficherVue("Medecin"));
        btnClient.addActionListener(e -> afficherVue("Client"));
        btnQuitter.addActionListener(e -> System.exit(0));

        return sidebar;
    }

    private JButton createSidebarButton(String text) {
        JButton button = new JButton(text);
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setMaximumSize(new Dimension(200, 50));
        button.setFocusPainted(false);
        return button;
    }

    public void afficherVue(String nomVue) {
        CardLayout cl = (CardLayout) (panelCentral.getLayout());
        cl.show(panelCentral, nomVue);
    }

    private String[] getListeClients() {
        return clientController.listerClients().stream()
                .map(client -> client.getNom() + " " + client.getPrenom())
                .toArray(String[]::new);
    }

    private String[] getListeMedecins() {
        return medecinController.listerMedecins().stream()
                .map(medecin -> medecin.getNom() + " " + medecin.getPrenom())
                .toArray(String[]::new);
    }

    // Méthode main pour lancer l'application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainDashboardView dashboard = new MainDashboardView();
            dashboard.setVisible(true);
        });
    }
}
