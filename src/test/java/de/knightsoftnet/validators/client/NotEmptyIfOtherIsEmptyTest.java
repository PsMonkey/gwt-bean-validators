package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.NotEmptyIfOtherIsEmpty;
import de.knightsoftnet.validators.shared.interfaces.HasGetFieldByName;

import org.junit.Test;

public class NotEmptyIfOtherIsEmptyTest extends
    AbstractValidationTest<NotEmptyIfOtherIsEmptyTest.TestBean> {

  @NotEmptyIfOtherIsEmpty.List({
      @NotEmptyIfOtherIsEmpty(field = "street", fieldCompare = "postOfficeBox"),
      @NotEmptyIfOtherIsEmpty(field = "postOfficeBox", fieldCompare = "street")})
  public class TestBean implements HasGetFieldByName {

    private final String street;

    private final String postOfficeBox;

    /**
     * constructor initializing fields.
     *
     * @param pstreet street name
     * @param ppostOfficeBox post office box
     */
    public TestBean(final String pstreet, final String ppostOfficeBox) {
      super();
      this.street = pstreet;
      this.postOfficeBox = ppostOfficeBox;
    }

    public String getStreet() {
      return this.street;
    }

    public String getPostOfficeBox() {
      return this.postOfficeBox;
    }

    @Override
    public final Object getFieldByName(final String pname) {
      if (pname != null) {
        switch (pname) {
          case "street":
            return this.street;
          case "postOfficeBox":
            return this.postOfficeBox;
          default:
            return null;
        }
      }
      return null;
    }
  }

  /**
   * both are filled is allowed.
   */
  @Test
  public final void testBothFilledIsAllowed() {
    super.validationTest(new TestBean("filled", "filled"), true, null);
  }

  /**
   * alternate fill is allowed.
   */
  @Test
  public final void testAlternateFillIsAllowed() {
    super.validationTest(new TestBean("filled", null), true, null);
    super.validationTest(new TestBean("filled", ""), true, null);
    super.validationTest(new TestBean(null, "filled"), true, null);
    super.validationTest(new TestBean("", "filled"), true, null);
  }

  /**
   * both entries are not allowed to be empty.
   */
  @Test
  public final void testBothEmptyIsWrong() {
    super.validationTest(new TestBean(null, null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
    super.validationTest(new TestBean(null, ""), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
    super.validationTest(new TestBean("", null), false,
        "de.knightsoftnet.validators.shared.impl.NotEmptyIfOtherIsEmptyValidator");
  }
}
