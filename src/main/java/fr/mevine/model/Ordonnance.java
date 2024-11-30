package fr.mevine.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.mevine.exceptions.InvalidInputException;

public class Ordonnance {
    private int id;
    private LocalDate date;
    private Medecin medecin;
    private Specialiste specialiste;
    private Client client;
    private List<Medicament> listeMedicaments;

    public Ordonnance(int id, LocalDate date, Client client, Medecin medecin, Specialiste specialiste) {
        setId(id);
        setDate(date);
        setClient(client);
        setPrescripteur(medecin, specialiste);
        this.listeMedicaments = new ArrayList<>();
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        if (date == null || date.isAfter(LocalDate.now())) {
            throw new InvalidInputException("La date de l'ordonnance ne peut pas être dans le futur.");
        }
        this.date = date;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public Specialiste getSpecialiste() {
        return specialiste;
    }

    public void setPrescripteur(Medecin medecin, Specialiste specialiste) {
        if ((medecin == null && specialiste == null) || (medecin != null && specialiste != null)) {
            throw new InvalidInputException("Une ordonnance doit être associée soit à un médecin, soit à un spécialiste.");
        }
        this.medecin = medecin;
        this.specialiste = specialiste;
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

    public List<Medicament> getListeMedicaments() {
        return new ArrayList<>(listeMedicaments);
    }

    public void ajouterMedicament(Medicament medicament) {
        if (medicament == null) {
            throw new InvalidInputException("Le médicament ne peut pas être null.");
        }
        listeMedicaments.add(medicament);
    }
}
