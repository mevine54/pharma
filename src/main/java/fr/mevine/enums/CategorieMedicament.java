package fr.mevine.enums;

import fr.mevine.exceptions.InvalidInputException;

/**
 * Enumération représentant les catégories de médicaments.
 */
public enum CategorieMedicament {
    ANTIBIOTIQUE,
    ANTALGIQUE,
    ANTIVIRAL;

    /**
     * Convertit une chaîne de caractères en CategorieMedicament.
     *
     * @param value La chaîne à convertir.
     * @return La catégorie correspondante.
     * @throws InvalidInputException Si la valeur ne correspond à aucune catégorie.
     */
    public static CategorieMedicament fromString(String value) {
        switch (value.toLowerCase()) {
            case "antibiotique":
                return ANTIBIOTIQUE;
            case "antalgique":
                return ANTALGIQUE;
            case "antiviral":
                return ANTIVIRAL;
            default:
                throw new InvalidInputException("Catégorie de médicament invalide : " + value);
        }
    }
}
