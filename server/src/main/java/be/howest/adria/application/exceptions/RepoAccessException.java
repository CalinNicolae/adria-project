package be.howest.adria.application.exceptions;

public class RepoAccessException extends RuntimeException {

    public RepoAccessException(String message) {
        super(message);
    }

    public RepoAccessException(String message, Throwable cause) {
        super(message, cause);
    }

    public RepoAccessException(Throwable cause) {
        super(cause);
    }

}
