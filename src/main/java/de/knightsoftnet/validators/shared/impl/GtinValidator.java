package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.Gtin;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.EAN13CheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid GTIN/EAN.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class GtinValidator implements ConstraintValidator<Gtin, Object> {

  /**
   * apache commons class to check/calculate GTIN/EAN check sums.
   */
  private static final EAN13CheckDigit CHECK_GTIN = new EAN13CheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final Gtin pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid gln.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      return true;
    }
    if (!StringUtils.isNumeric(valueAsString)) {
      // EAN must be numeric, but that's handled by digits annotation
      return true;
    }
    if (valueAsString.length() != Gtin8Validator.GTIN8_LENGTH
        && valueAsString.length() != Gtin13Validator.GTIN13_LENGTH) {
      // EAN size is wrong, but that's handled by alternate size
      // annotation
      return true;
    }
    // calculate and check checksum (GTIN/EAN)
    return CHECK_GTIN.isValid(valueAsString);
  }
}
