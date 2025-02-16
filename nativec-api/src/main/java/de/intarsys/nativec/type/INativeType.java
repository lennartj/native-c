/*-
 * #%L
 * Nazgul Project: nativec-api
 * %%
 * Copyright (C) 2013 - 2022 Intarsys Consulting GmbH
 * %%
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * - Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 * 
 * - Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 * 
 * - Neither the name of intarsys nor the names of its contributors may be used
 *   to endorse or promote products derived from this software without specific
 *   prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 * #L%
 */
package de.intarsys.nativec.type;

import de.intarsys.nativec.api.INativeHandle;
public interface INativeType {

  /**
   * Create an array type from this.
   *
   * @param size The predefined size for the array.
   *
   * @return The derived type.
   */
  INativeType Array(int size);

  /**
   * Create an {@link INativeObject} for this type from the Java object.
   *
   * @param value
   *
   * @return The new {@link INativeObject}
   */
  INativeObject createNative(Object value);

  /**
   * Create a new {@link INativeObject} from a {@link INativeHandle}.
   *
   * @param handle The handle to memory.
   *
   * @return The new {@link INativeObject}
   */
  INativeObject createNative(INativeHandle handle);

  /**
   * The boundary where this type as a struct member would want to be aligned.
   * A structure can override this value with packing.
   *
   * @return The preferred alignment boundary.
   */
  int getPreferredBoundary();

  /**
   * The size of the type in c memory.
   *
   * @return The size of the type in c memory.
   */
  int getByteCount();

  /**
   * Create a reference type to this.
   *
   * @return The derived type.
   */
  INativeType Ref();
}
