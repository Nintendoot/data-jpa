package by.nintendo.datajpa.exception;

public class NoOrdersException extends RuntimeException{
    public NoOrdersException() {
        super();
    }

    public NoOrdersException(String message) {
        super(message);
    }

    public NoOrdersException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoOrdersException(Throwable cause) {
        super(cause);
    }
}
