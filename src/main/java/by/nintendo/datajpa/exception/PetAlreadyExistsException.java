package by.nintendo.datajpa.exception;

public class PetAlreadyExistsException extends RuntimeException{
    public PetAlreadyExistsException() {
        super();
    }

    public PetAlreadyExistsException(String message) {
        super(message);
    }

    public PetAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public PetAlreadyExistsException(Throwable cause) {
        super(cause);
    }

    protected PetAlreadyExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
