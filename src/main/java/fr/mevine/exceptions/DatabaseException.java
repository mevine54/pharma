package fr.mevine.exceptions;

/**
 * Exception personnalisée pour gérer les erreurs liées aux interactions avec la base de données.
 */
public class DatabaseException extends RuntimeException {
    /**
     * Constructeur avec un message d'erreur.
     *
     * @param message Le message décrivant l'erreur.
     */
    public DatabaseException(String message) {
        super(message);
    }

    /**
     * Constructeur avec un message d'erreur et une cause.
     *
     * @param message Le message décrivant l'erreur.
     * @param cause   L'exception qui a causé cette erreur.
     */
    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
