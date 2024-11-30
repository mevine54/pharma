package fr.mevine.view;

import javax.swing.*;
import java.awt.*;

public class AccueilView extends JPanel {

    public AccueilView() {
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JLabel labelAccueil = new JLabel("Bienvenue dans la Pharmacie Sparadrap", SwingConstants.CENTER);
        labelAccueil.setFont(new Font("Arial", Font.BOLD, 24));
        add(labelAccueil, BorderLayout.CENTER);
    }
}
