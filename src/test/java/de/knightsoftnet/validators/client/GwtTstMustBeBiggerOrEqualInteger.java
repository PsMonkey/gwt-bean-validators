/*
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license
 * agreements. See the NOTICE file distributed with this work for additional information regarding
 * copyright ownership. The ASF licenses this file to You under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance with the License. You may obtain a
 * copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client;

import de.knightsoftnet.validators.shared.beans.MustBeBiggerOrEqualIntegerTestBean;
import de.knightsoftnet.validators.shared.testcases.MustBeBiggerOrEqualIntegerTestCases;

/**
 * test for must be bigger or equal validator with integer values.
 *
 * @author Manfred Tremmel
 *
 */
public class GwtTstMustBeBiggerOrEqualInteger
    extends AbstractValidationTst<MustBeBiggerOrEqualIntegerTestBean> {

  /**
   * both entries are empty, allowed.
   */
  public final void testBothEmptyIsAllowed() {
    for (final MustBeBiggerOrEqualIntegerTestBean testBean : MustBeBiggerOrEqualIntegerTestCases
        .getEmptyTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries are equal, allowed.
   */
  public final void testBothEqualIsAllowed() {
    for (final MustBeBiggerOrEqualIntegerTestBean testBean : MustBeBiggerOrEqualIntegerTestCases
        .getCorrectTestBeans()) {
      super.validationTest(testBean, true, null);
    }
  }

  /**
   * both entries differ, wrong.
   */
  public final void testBothDifferWrong() {
    for (final MustBeBiggerOrEqualIntegerTestBean testBean : MustBeBiggerOrEqualIntegerTestCases
        .getWrongTestBeans()) {
      super.validationTest(testBean, false,
          "de.knightsoftnet.validators.shared.impl.MustBeBiggerOrEqualValidator");
    }
  }
}
