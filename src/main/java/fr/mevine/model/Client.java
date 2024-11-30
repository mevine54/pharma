package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;
import fr.mevine.util.Validator;

import java.time.LocalDate;

public class Client extends Utilisateur {
    private String securiteSociale;
    private LocalDate dateNaissance;
    private Mutuelle mutuelle;

    // Création de mutuelles
    Mutuelle mutuelle1 = new Mutuelle("Mutuelle Santé Plus", getAdresse(),  30);
    Mutuelle mutuelle2 = new Mutuelle("Mutuelle Bien-Être", getAdresse(),  30);

    public Client(int id, String nom, String prenom, String email, String motDePasse, Adresse adresse, String telephone,
                  String securiteSociale, LocalDate dateNaissance, Mutuelle mutuelle) {
        super(id, nom, prenom, email, motDePasse, adresse, telephone);
        setSecuriteSociale(securiteSociale);
        setDateNaissance(dateNaissance);
        setMutuelle(mutuelle);
    }

    public String getSecuriteSociale() {
        return securiteSociale;
    }

    public void setSecuriteSociale(String securiteSociale) {
        if (!Validator.isValidSecuriteSociale(securiteSociale)) {
            throw new InvalidInputException("Numéro de sécurité sociale invalide.");
        }
        this.securiteSociale = securiteSociale;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
//        if (!Validator.isValidDate(dateNaissance)) {
//            throw new InvalidInputException("Date de naissance invalide.");
//        }
//        this.dateNaissance =  dateNaissance;

        if (dateNaissance != null && dateNaissance.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("La date de naissance ne peut pas être dans le futur.");
        }
        this.dateNaissance = dateNaissance;
    }

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        if (mutuelle == null) {
            throw new InvalidInputException("La mutuelle ne peut pas être null.");
        }
        this.mutuelle = mutuelle;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numéro SS: " + securiteSociale + ", Date de naissance: " + dateNaissance;
    }
}
