package nu.nooris.noorisbackend.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import nu.nooris.noorisbackend.repository.entity.Booking;

public interface BookingService {

  List<Booking> getAllBookings();

  List<Booking> getAllBookingsByDate(LocalDate date);

  Booking getBookingByReference(UUID reference);

  Booking createBooking(Booking booking);

  List<LocalDateTime> getAvailableTimeSlots(LocalDate date, int guests);

  void deleteBookingByReference(UUID reference);
}
