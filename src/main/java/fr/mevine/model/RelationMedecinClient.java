package fr.mevine.model;

import fr.mevine.enums.TypeRelation;
import fr.mevine.exceptions.InvalidInputException;

import java.time.LocalDate;

public class RelationMedecinClient {
    private int id;
    private Client client;
    private Medecin medecin;
    private TypeRelation typeRelation;
    private LocalDate dateDebutRelation;
    private LocalDate dateFinRelation;

    public RelationMedecinClient(int id, Client client, Medecin medecin, TypeRelation typeRelation,
                                 LocalDate dateDebutRelation, LocalDate dateFinRelation) {
        setId(id);
        setClient(client);
        setMedecin(medecin);
        setTypeRelation(typeRelation);
        setDateDebutRelation(dateDebutRelation);
        setDateFinRelation(dateFinRelation);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new InvalidInputException("L'ID doit être un entier positif.");
        }
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        if (client == null) {
            throw new InvalidInputException("Le client ne peut pas être null.");
        }
        this.client = client;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        if (medecin == null) {
            throw new InvalidInputException("Le médecin ne peut pas être null.");
        }
        this.medecin = medecin;
    }

    public TypeRelation getTypeRelation() {
        return typeRelation;
    }

    public void setTypeRelation(TypeRelation typeRelation) {
        if (typeRelation == null) {
            throw new InvalidInputException("Le type de relation ne peut pas être null.");
        }
        this.typeRelation = typeRelation;
    }

    public LocalDate getDateDebutRelation() {
        return dateDebutRelation;
    }

    public void setDateDebutRelation(LocalDate dateDebutRelation) {
        if (dateDebutRelation == null) {
            throw new InvalidInputException("La date de début de relation ne peut pas être null.");
        }
        this.dateDebutRelation = dateDebutRelation;
    }

    public LocalDate getDateFinRelation() {
        return dateFinRelation;
    }

    public void setDateFinRelation(LocalDate dateFinRelation) {
        if (dateFinRelation != null && dateFinRelation.isBefore(dateDebutRelation)) {
            throw new InvalidInputException("La date de fin de relation ne peut pas être avant la date de début.");
        }
        this.dateFinRelation = dateFinRelation;
    }
}
