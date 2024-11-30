package fr.mevine.model;


public class Manager extends Utilisateur {

    public Manager(int id, String nom, String prenom, String email, String motDePasse, Adresse adresse, String telephone) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone);
    }

    // Vous pouvez ajouter des attributs ou méthodes spécifiques à Manager ici
}

