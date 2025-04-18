package nu.nooris.noorisbackend.service;

import nu.nooris.noorisbackend.repository.entity.Booking;

public interface EmailService {

  void sendBookingEmail(String to, Booking booking);
}
