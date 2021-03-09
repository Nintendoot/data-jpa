package by.nintendo.datajpa.exception;

public class PetStatusException extends RuntimeException{
    public PetStatusException() {
        super();
    }

    public PetStatusException(String message) {
        super(message);
    }

    public PetStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public PetStatusException(Throwable cause) {
        super(cause);
    }

    protected PetStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
