package fr.mevine.model;



import fr.mevine.exceptions.InvalidInputException;

public class Adresse {
    private String rue;
    private String codePostal;
    private String ville;

    public Adresse(String rue, String codePostal, String ville) {
        setRue(rue);
        setCodePostal(codePostal);
        setVille(ville);
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        if (rue == null || rue.isEmpty()) {
            throw new InvalidInputException("La rue ne peut pas être vide.");
        }
        this.rue = rue;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        if (codePostal == null || !codePostal.matches("\\d{5}")) {
            throw new InvalidInputException("Code postal invalide.");
        }
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        if (ville == null || ville.isEmpty()) {
            throw new InvalidInputException("La ville ne peut pas être vide.");
        }
        this.ville = ville;
    }
}
