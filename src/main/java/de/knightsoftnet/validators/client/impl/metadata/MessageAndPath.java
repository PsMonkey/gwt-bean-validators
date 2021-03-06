/*
 * Copyright 2010 Google Inc. Copyright 2016 Manfred Tremmel
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.knightsoftnet.validators.client.impl.metadata;

import javax.validation.Path;

/**
 * Holds as Message and a Path.
 * <p>
 * Used to build a {@link javax.validation.ConstraintViolation}
 * </p>
 */
public final class MessageAndPath {

  private final String message;
  private final Path path;

  /**
   * constructor.
   * 
   * @param path path of field
   * @param message error message
   */
  public MessageAndPath(final Path path, final String message) {
    super();
    this.path = path;
    this.message = message;
  }

  public String getMessage() {
    return this.message;
  }

  public Path getPath() {
    return this.path;
  }

  /**
   * For debugging use only.
   */
  @Override
  public String toString() {
    return this.path + "=" + this.message;
  }
}
