package fr.mevine.util;

import java.time.LocalDate;

public class Validator {

    public static boolean isValidNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    public static boolean isValidSecuriteSociale(String numero) {
        return numero != null && numero.matches("\\d{15}");
    }

    public static boolean isValidCodePostal(String codePostal) {
        return codePostal != null && codePostal.matches("\\d{5}");
    }

    public static boolean isValidMotDePasse(String motDePasse) {
        return motDePasse != null && motDePasse.length() >= 8;
    }

    public static boolean isValidDate(String date) {
        return date != null && date.matches("\\d{4}-\\d{2}-\\d{2}");
    }

    public static boolean isPastOrToday(LocalDate date) {
        return date != null && !date.isAfter(LocalDate.now());
    }

    public static boolean isPositiveDouble(double value) {
        return value > 0;
    }

    public static boolean isNonNegativeInt(int value) {
        return value >= 0;
    }


}
