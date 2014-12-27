package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.Isbn13;

import org.junit.Test;

/**
 * test for isbn 13 validator.
 *
 * @author Manfred Tremmel
 *
 */
public class Isbn13Test extends AbstractValidationTest<Isbn13Test.Isbn13TestBean> {

  /**
   * test bean with isbn number.
   */
  public class Isbn13TestBean {

    /** isbn. */
    @Isbn13
    private final String isbn;

    /**
     * constructor initializing fields.
     *
     * @param pisbn isbn to set
     */
    public Isbn13TestBean(final String pisbn) {
      super();
      this.isbn = pisbn;
    }

    public final String getIsbn() {
      return this.isbn;
    }
  }

  /**
   * empty isbn13 is allowed.
   */
  @Test
  public final void testEmptyIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean(null), true, null);
  }

  /**
   * correct isbn13 is allowed.
   */
  @Test
  public final void testCorrectIsbn13IsAllowed() {
    super.validationTest(new Isbn13TestBean("9783836218023"), true, null);
    super.validationTest(new Isbn13TestBean("9783836215077"), true, null);
    super.validationTest(new Isbn13TestBean("9783898644716"), true, null);
  }

  /**
   * isbn13 with wrong checksum.
   */
  @Test
  public final void testWrongChecksumIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("9783836218032"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    super.validationTest(new Isbn13TestBean("9783838215077"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
    super.validationTest(new Isbn13TestBean("9783899644716"), false,
        "de.knightsoftnet.validators.shared.impl.Isbn13Validator");
  }

  /**
   * isbn13 which is to small.
   */
  @Test
  public final void testToSmallIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978383621803"), false,
        "org.hibernate.validator.constraints.impl.SizeValidatorForString");
  }

  /**
   * isbn13 which is to big.
   */
  @Test
  public final void testToBigIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("97838362180321"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }

  /**
   * isbn13 which is not numeric.
   */
  @Test
  public final void testNotNumericIsbn13IsWrong() {
    super.validationTest(new Isbn13TestBean("978383621803Y"), false,
        "org.hibernate.validator.constraints.impl.DigitsValidatorForString");
  }
}
