package fr.mevine.view;

import fr.mevine.view.MainDashboardView;
import fr.mevine.controller.UtilisateurController;
import fr.mevine.model.Utilisateur;
import fr.mevine.util.Validator;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField emailField;
    private JPasswordField motDePasseField;
    private JButton connexionButton;

    private UtilisateurController utilisateurController;

    public LoginView() {
        utilisateurController = new UtilisateurController();
        initComponents();
    }

    private void initComponents() {
        setTitle("Connexion");
        setSize(350, 180);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel emailLabel = new JLabel("Email:");
        JLabel motDePasseLabel = new JLabel("Mot de passe:");

        emailField = new JTextField(20);
        motDePasseField = new JPasswordField(20);

        connexionButton = new JButton("Se connecter");

        JPanel panel = new JPanel();
        panel.setLayout(null);

        emailLabel.setBounds(30, 20, 100, 25);
        emailField.setBounds(140, 20, 160, 25);

        motDePasseLabel.setBounds(30, 60, 100, 25);
        motDePasseField.setBounds(140, 60, 160, 25);

        connexionButton.setBounds(140, 100, 120, 25);

        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(motDePasseLabel);
        panel.add(motDePasseField);
        panel.add(connexionButton);

        add(panel);

        connexionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                seConnecter();
            }
        });
    }

    private void seConnecter() {
        String email = emailField.getText();
        String motDePasse = new String(motDePasseField.getPassword());

        if (!Validator.isValidEmail(email)) {
            JOptionPane.showMessageDialog(this, "Format d'email invalide.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!Validator.isValidMotDePasse(motDePasse)) {
            JOptionPane.showMessageDialog(this, "Le mot de passe doit contenir au moins 8 caractères.", "Erreur", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Utilisateur utilisateur = utilisateurController.authentifierUtilisateur(email, motDePasse);

        if (utilisateur != null) {
            // Ouvrir le tableau de bord principal
            MainDashboardView dashboard = new MainDashboardView(); // Passer l'utilisateur si nécessaire
            dashboard.setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.", "Erreur", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginView().setVisible(true));
    }
}

