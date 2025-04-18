package nu.nooris.noorisbackend.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.common.TimeRange;
import nu.nooris.noorisbackend.config.BookingProperties;
import nu.nooris.noorisbackend.repository.BookingRepository;
import nu.nooris.noorisbackend.repository.entity.Booking;
import nu.nooris.noorisbackend.service.BookingService;
import nu.nooris.noorisbackend.service.exception.BookingNotFoundException;
import nu.nooris.noorisbackend.service.exception.InvalidBookingTimeException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

  private final BookingRepository bookingRepository;
  private final BookingProperties bookingProperties;

  @Value("${app.booking.duration-hours}")
  private int durationHours;

  @Value("${app.booking.slot-interval}")
  private int slotInterval;

  @Override
  public List<Booking> getAllBookings() {
    LocalDateTime now = LocalDateTime.now();
    return bookingRepository.findAllByStartTimeAfterOrderByStartTimeAsc(now);
  }

  @Override
  public List<Booking> getAllBookingsByDate(LocalDate date) {
    LocalDateTime now = LocalDateTime.now();
    LocalDateTime startOfDay = date.atStartOfDay();
    LocalDateTime endOfDay = date.plusDays(1).atStartOfDay();
    return bookingRepository.findAllByStartTimeBetweenAndStartTimeAfterOrderByStartTimeAsc(
        startOfDay, endOfDay, now);
  }

  @Override
  public Booking getBookingByReference(UUID reference) {
    return bookingRepository.findById(reference)
        .orElseThrow(() -> new BookingNotFoundException(reference));
  }

  @Override
  public Booking createBooking(Booking booking) {
    LocalDateTime start = booking.getStartTime();
    LocalDateTime end = booking.getStartTime().plusHours(2);
    LocalDateTime latestNow = LocalDateTime.now().plusMinutes(30);
    if (start.isBefore(latestNow)) {
      throw new InvalidBookingTimeException("Booking start time must be in the future");
    }
    LocalDate date = start.toLocalDate();
    TimeRange openingHours = bookingProperties.getOpeningHoursForDate(date);
    if (openingHours == null ||
        start.isBefore(date.atTime(openingHours.start())) ||
        end.isAfter(date.atTime(openingHours.end()))) {
      throw new InvalidBookingTimeException("Booking time is outside of opening hours");
    }
    return bookingRepository.save(booking);
  }

  @Override
  public List<LocalDateTime> getAvailableTimeSlots(LocalDate date, int guests) {
    List<LocalDateTime> availableSlots = new ArrayList<>();
    TimeRange openingHours = bookingProperties.getOpeningHoursForDate(date);
    if (openingHours == null) {
      return Collections.emptyList();
    }
    LocalDateTime currentSlot = date.atTime(openingHours.start());
    LocalDateTime slotEnd = currentSlot.plusHours(durationHours);
    LocalDateTime latestNow = LocalDateTime.now().plusMinutes(30);
    while (!slotEnd.isAfter(date.atTime(openingHours.end()))) {
      if (currentSlot.isBefore(latestNow)) {
        currentSlot = currentSlot.plusMinutes(slotInterval);
        slotEnd = currentSlot.plusHours(durationHours);
        continue;
      }
      availableSlots.add(currentSlot);
      currentSlot = currentSlot.plusMinutes(slotInterval);
      slotEnd = currentSlot.plusHours(durationHours);
    }
    return availableSlots;
  }

  @Override
  public void deleteBookingByReference(UUID reference) {
    bookingRepository.deleteById(reference);
  }
}
