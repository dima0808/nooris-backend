package nu.nooris.noorisbackend.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VariantsValidator.class)
public @interface ValidVariants {

  String message() default "Label must be null when there is only one variant, and must not be null when there are multiple variants";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
