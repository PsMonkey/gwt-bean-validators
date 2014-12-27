package de.knightsoftnet.validators.shared;

import de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherHasValueValidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Check if a field is filled if another field has a given value.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
@Documented
@Constraint(validatedBy = NotEmptyIfOtherHasValueValidator.class)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotEmptyIfOtherHasValue {
  /**
   * localized message.
   */
  String message() default "{deKnightsoftnetValidatorsSharedValidationNotEmptyIfOtherHasValue"
      + "Message}";

  /**
   * groups to use.
   */
  Class<?>[] groups() default {};

  /**
   * field name to check.
   */
  String field();

  /**
   * field name to compare.
   */
  String fieldCompare();

  /**
   * value to compare with field name to compare.
   */
  String valueCompare();

  /**
   * payload whatever.
   */
  Class<? extends Payload>[] payload() default {};

  /**
   * Defines several {@code @MustBeFilledIfOtherHasValue} annotations on the same element.
   */
  @Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
  @Retention(RetentionPolicy.RUNTIME)
  @Documented
  public @interface List {
    /**
     * must be filled if other has the given value.
     */
    NotEmptyIfOtherHasValue[] value();
  }
}
