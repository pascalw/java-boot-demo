package nl.kabisa.service.booking;

public class BookingException extends RuntimeException {
    public static class NoRatesFoundException extends BookingException {}

    public BookingException() {
    }

    public BookingException(String message) {
        super(message);
    }
}
