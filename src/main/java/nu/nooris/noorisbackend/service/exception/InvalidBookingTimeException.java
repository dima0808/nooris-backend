package nu.nooris.noorisbackend.service.exception;

public class InvalidBookingTimeException extends RuntimeException {

  public InvalidBookingTimeException(String message) {
    super(message);
  }
}
