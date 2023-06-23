package uz.in.domain.exception;

public class DataNotFoundException extends RuntimeException{
    public DataNotFoundException() {
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
