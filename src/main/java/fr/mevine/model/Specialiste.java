package fr.mevine.model;

import fr.mevine.enums.Specialite;
import fr.mevine.exceptions.InvalidInputException;

public class Specialiste extends Medecin {
    private Specialite specialite;

    public Specialiste(int id, String nom, String prenom, String email, String motDePasse, Adresse adresse, String telephone,
                       String numeroAgrement, Specialite specialite) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone, numeroAgrement);
        setSpecialite(specialite);
    }

    public Specialite getSpecialite() {
        return specialite;
    }

    public void setSpecialite(Specialite specialite) {
        if (specialite == null) {
            throw new InvalidInputException("La spécialité ne peut pas être null.");
        }
        this.specialite = specialite;
    }
}
