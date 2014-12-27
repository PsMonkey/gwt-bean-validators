package de.knightsoftnet.validators.shared.impl;

import de.knightsoftnet.validators.shared.IbanFormated;
import de.knightsoftnet.validators.shared.data.SwiftDefinitions;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.checkdigit.IBANCheckDigit;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Check a string if it's a valid IBAN formated.
 *
 * @author Manfred Tremmel
 * @version $Rev$, $Date$
 *
 */
public class IbanFormatedValidator implements ConstraintValidator<IbanFormated, Object> {
  /**
   * definition of IBAN length minimum.
   */
  public static final int IBAN_LENGTH_MIN = 19;
  /**
   * definition of IBAN length maximum.
   */
  public static final int IBAN_LENGTH_MAX = 42;

  /**
   * apache commons class to check/calculate IBAN check sums.
   */
  private static final IBANCheckDigit CHECK_IBAN = new IBANCheckDigit();

  /**
   * {@inheritDoc} initialize the validator.
   *
   * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
   */
  @Override
  public final void initialize(final IbanFormated pconstraintAnnotation) {
    // nothing to do
  }

  /**
   * {@inheritDoc} check if given string is a valid IBAN.
   *
   * @see javax.validation.ConstraintValidator#isValid(java.lang.Object,
   *      javax.validation.ConstraintValidatorContext)
   */
  @Override
  public final boolean isValid(final Object pvalue, final ConstraintValidatorContext pcontext) {
    final String valueAsString = ObjectUtils.toString(pvalue);
    if (StringUtils.isEmpty(valueAsString)) {
      // empty field is ok
      return true;
    }
    if (valueAsString.length() < IBAN_LENGTH_MIN || valueAsString.length() > IBAN_LENGTH_MAX) {
      // to short or to long, but it's handled by size validator!
      return true;
    }
    final String countryCode = valueAsString.substring(0, 2);
    final Integer validIbanLength = SwiftDefinitions.COUNTRY_IBAN_LENGTH.get(countryCode);
    if (validIbanLength == null
        || valueAsString.replaceAll("\\s", "").length() != validIbanLength.intValue()) {
      // unknown country or wrong length for the country!
      return false;
    }
    if (!valueAsString.matches("^[A-Z]{2}[0-9A-Z]{2} ([0-9A-Z]{4}[ ])+[0-9A-Z]{1,4}$")) {
      // wrong format
      return false;
    }

    return CHECK_IBAN.isValid(valueAsString.replaceAll("\\s", ""));
  }

}
