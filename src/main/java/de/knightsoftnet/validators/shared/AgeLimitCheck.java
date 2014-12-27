package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.AgeLimitCheckValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * A age check, if it is a minimum years ago - interface.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = AgeLimitCheckValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
  ElementType.CONSTRUCTOR, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeLimitCheck {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationAgeLimitCheckMessage}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * minimum years.
   */
  int minYears();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code AgeLimitCheck} annotations on the same element.
   */
  @Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE,
    ElementType.CONSTRUCTOR, ElementType.PARAMETER})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other is empty value.
     */
    AgeLimitCheck[] value();
  }
}
