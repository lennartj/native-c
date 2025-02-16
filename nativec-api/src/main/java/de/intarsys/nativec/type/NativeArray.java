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
public class NativeArray extends NativeObject {

  /**
   * The meta class instance
   */
  public static final NativeArrayType META = new NativeArrayType(
    NativeVoid.META, 0);

  static {
    NativeType.register(NativeArray.class, META);
  }

  private INativeObject[] values;
  private NativeArrayType type;

  protected NativeArray(NativeArrayType type) {
    this.type = type;
    allocate();
  }

  protected NativeArray(NativeArrayType type, INativeHandle handle) {
    super(handle);
    this.type = type;
  }

  public static NativeArray create(INativeType baseType, int size) {
    NativeArrayType type = new NativeArrayType(baseType, size);
    return new NativeArray(type);
  }

  public INativeType getBaseType() {
    return type.getBaseType();
  }

  public void setBaseType(INativeType baseType) {
    type = NativeArrayType.create(baseType, type.getArraySize());
    this.values = null;
    handle.setSize(getByteCount());
  }

  @Override
  public int getByteCount() {
    return type.getByteCount();
  }

  /**
   * The {@link INativeObject} at index in the sequence (the index'th element
   * of the array).
   *
   * @param index The index of the element to be reported.
   *
   * @return The NativeObject at index
   */
  synchronized public INativeObject getNativeObject(int index) {
    if (values == null) {
      values = new INativeObject[getSize()];
    }
    INativeObject result = values[index];
    if (result == null) {
      int elementOffset = index * type.getBaseSize();
      result = type.getBaseType().createNative(
        handle.offset(elementOffset));
      values[index] = result;
    }
    return result;
  }

  /*
   * (non-Javadoc)
   *
   * @see de.intarsys.graphic.freetype.NativeObject#getMetaClass()
   */
  @Override
  public INativeType getNativeType() {
    return type;
  }

  /**
   * The number of NativeObject instances in the sequence represented by this
   * (in other terms the array size).
   *
   * @return The number of NativeObject instances in the sequence represented
   * by this
   */
  public int getSize() {
    return type.getArraySize();
  }

  public void setSize(int size) {
    type = NativeArrayType.create(type.getBaseType(), size);
    this.values = null;
    handle.setSize(getByteCount());
  }

  public Object getValue() {
    throw new UnsupportedOperationException(
      "getValue not implemented for NativeArray");
  }

  public void setValue(Object value) {
    throw new UnsupportedOperationException(
      "setValue not implemented for NativeArray");
  }

  public Object getValue(int index) {
    return getNativeObject(index).getValue();
  }

  public void setValue(int index, Object value) {
    getNativeObject(index).setValue(value);
  }

  /*
   * (non-Javadoc)
   *
   * @see de.intarsys.graphic.freetype.NativeObject#toNestedString()
   */
  @Override
  public String toNestedString() {
    return "[...]";
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < getSize(); i++) {
      // NativeObject element = get(i);
      // sb.append(element.toString());
      // sb.append(", ");
    }
    sb.append("]");
    return sb.toString();
  }
}
