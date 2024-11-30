package fr.mevine.model;

import fr.mevine.exceptions.InvalidInputException;

import java.time.LocalDate;

public class AffiliationMutuelle {
    private int id;
    private Client client;
    private Mutuelle mutuelle;
    private LocalDate dateAffiliation;
    private LocalDate dateFinAffiliation;
    private double tauxRemboursementPersonnalise;

    public AffiliationMutuelle(int id, Client client, Mutuelle mutuelle, LocalDate dateAffiliation,
                               LocalDate dateFinAffiliation, double tauxRemboursementPersonnalise) {
        setId(id);
        setClient(client);
        setMutuelle(mutuelle);
        setDateAffiliation(dateAffiliation);
        setDateFinAffiliation(dateFinAffiliation);
        setTauxRemboursementPersonnalise(tauxRemboursementPersonnalise);
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

    public Mutuelle getMutuelle() {
        return mutuelle;
    }

    public void setMutuelle(Mutuelle mutuelle) {
        if (mutuelle == null) {
            throw new InvalidInputException("La mutuelle ne peut pas être null.");
        }
        this.mutuelle = mutuelle;
    }

    public LocalDate getDateAffiliation() {
        return dateAffiliation;
    }

    public void setDateAffiliation(LocalDate dateAffiliation) {
        if (dateAffiliation == null) {
            throw new InvalidInputException("La date d'affiliation ne peut pas être null.");
        }
        this.dateAffiliation = dateAffiliation;
    }

    public LocalDate getDateFinAffiliation() {
        return dateFinAffiliation;
    }

    public void setDateFinAffiliation(LocalDate dateFinAffiliation) {
        if (dateFinAffiliation != null && dateFinAffiliation.isBefore(dateAffiliation)) {
            throw new InvalidInputException("La date de fin d'affiliation ne peut pas être avant la date d'affiliation.");
        }
        this.dateFinAffiliation = dateFinAffiliation;
    }

    public double getTauxRemboursementPersonnalise() {
        return tauxRemboursementPersonnalise;
    }

    public void setTauxRemboursementPersonnalise(double tauxRemboursementPersonnalise) {
        if (tauxRemboursementPersonnalise < 0 || tauxRemboursementPersonnalise > 100) {
            throw new InvalidInputException("Le taux de remboursement doit être entre 0 et 100.");
        }
        this.tauxRemboursementPersonnalise = tauxRemboursementPersonnalise;
    }
}
