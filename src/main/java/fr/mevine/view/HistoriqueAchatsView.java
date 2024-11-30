package fr.mevine.view;

import fr.mevine.controller.ClientController;
import fr.mevine.controller.MedecinController;
import javax.swing.*;
import java.awt.*;

public class HistoriqueAchatsView extends JPanel {
    private ClientController clientController;
    private MedecinController medecinController;
    private MainDashboardView mainDashboardView;

    public HistoriqueAchatsView(ClientController clientController, MedecinController medecinController, MainDashboardView mainDashboardView) {
        this.clientController = clientController;
        this.medecinController = medecinController;
        this.mainDashboardView = mainDashboardView;
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel labelHistorique = new JLabel("Historique des Achats", SwingConstants.CENTER);
        labelHistorique.setFont(new Font("Arial", Font.BOLD, 18));

        // Contenu de l'historique (à adapter selon vos besoins)
        JTextArea historiqueArea = new JTextArea();
        historiqueArea.setEditable(false);
        historiqueArea.setText("Voici l'historique des achats.");

        // Bouton retour
        JButton btnRetour = new JButton("Retour");
        btnRetour.addActionListener(e -> {
            if (mainDashboardView != null) {
                mainDashboardView.afficherVue("Accueil");
            }
        });

        add(labelHistorique, BorderLayout.NORTH);
        add(new JScrollPane(historiqueArea), BorderLayout.CENTER);
        add(btnRetour, BorderLayout.SOUTH);
    }
}
