package nu.nooris.noorisbackend.service.impl;

import lombok.RequiredArgsConstructor;
import nu.nooris.noorisbackend.repository.entity.Booking;
import nu.nooris.noorisbackend.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

  private final JavaMailSender mailSender;

  @Override
  public void sendBookingEmail(String to, Booking booking) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom("booking@nooris.nu");
    message.setTo(to);
    message.setSubject("New Booking Received. " + booking.getName());
    message.setText(buildBookingEmailContent(booking));
    mailSender.send(message);
  }

  private String buildBookingEmailContent(Booking booking) {
    return String.format(
        """
            New booking received:
            
            Name: %s
            Phone Number: %s
            Email: %s
            Guests: %d
            Start Time: %s
            """,
        booking.getName(),
        booking.getPhoneNumber(),
        booking.getEmail() == null ? "N/A" : booking.getEmail(),
        booking.getGuests(),
        booking.getStartTime().toString().replace("T", " ")
    );
  }
}
