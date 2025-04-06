package nu.nooris.noorisbackend.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.List;
import nu.nooris.noorisbackend.dto.MenuItemVariantDto;

public class VariantsValidator implements
    ConstraintValidator<ValidVariants, List<MenuItemVariantDto>> {

  @Override
  public boolean isValid(List<MenuItemVariantDto> variants, ConstraintValidatorContext context) {
    if (variants.size() == 1) {
      return true;
    }
    return variants.stream().allMatch(v -> v.getLabel() != null);
  }
}
