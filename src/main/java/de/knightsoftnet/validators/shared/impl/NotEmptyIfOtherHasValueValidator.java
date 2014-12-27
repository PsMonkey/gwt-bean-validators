package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.NotEmptyIfOtherHasValue;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check if a field is filled if another field has a given value.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class NotEmptyIfOtherHasValueValidator implements
    ConstraintValidator<NotEmptyIfOtherHasValue, HasGetFieldByName> {

  /**
   * error message key.
   */
  private String message;
  /**
   * field name to check.
   */
  private String fieldCheckName;
  /**
   * field name to compare.
   */
  private String fieldCompareName;

  /**
   * value to compare with field name to compare.
   */
  private String valueCompare;

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final NotEmptyIfOtherHasValue pconstraintAnnotation) {
    this.message = pconstraintAnnotation.message();
    this.fieldCheckName = pconstraintAnnotation.field();
    this.fieldCompareName = pconstraintAnnotation.fieldCompare();
    this.valueCompare = pconstraintAnnotation.valueCompare();
  }

  /**
   * {@inheritDoc} check if given object.
   *
   * @see javax.validation.ConstraintValidator#isValid(HasGetFieldByName,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final HasGetFieldByName pvalue,
      final ConstraintValidatorContext pcontext) {
    try {
      final String fieldCheckValue =
          Objects.toString(pvalue.getFieldByName(this.fieldCheckName), null);
      final String fieldCompareValue =
          Objects.toString(pvalue.getFieldByName(this.fieldCompareName), null);
      if (StringUtils.isEmpty(fieldCheckValue)
          && StringUtils.equals(this.valueCompare, fieldCompareValue)) {
        pcontext.disableDefaultConstraintViolation();
        pcontext.buildConstraintViolationWithTemplate(this.message).addNode(this.fieldCheckName)
            .addConstraintViolation();
        return false;
      }
      return true;
    } catch (final Exception ignore) {
      return false;
    }
  }
}
