package uz.in.domain.exception;

public class DublicateDataValueException extends RuntimeException{
    public DublicateDataValueException() {
    }

    public DublicateDataValueException(String message) {
        super(message);
    }
}
