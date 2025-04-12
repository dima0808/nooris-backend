package nu.nooris.noorisbackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;

public class GuestsValidator implements ConstraintValidator<ValidGuests, Integer> {

  @Value("${app.booking.max-guests}")
  private int maxGuests;

  @Override
  public boolean isValid(Integer value, ConstraintValidatorContext context) {
    if (value == null || value < 1 || value > maxGuests) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(
          "Number of guests must be between 1 and " + maxGuests).addConstraintViolation();
      return false;
    }
    return true;
  }
}
