package nu.nooris.noorisbackend.service.exception;

import java.util.UUID;

public class BookingNotFoundException extends RuntimeException {

  public BookingNotFoundException(UUID reference) {
    super("Booking with reference " + reference + " not found");
  }
}
