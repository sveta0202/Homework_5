package exceptions;

public class InvalidReservationNumberException extends RuntimeException {
    public InvalidReservationNumberException(String message) {
        super(message);
    }
}