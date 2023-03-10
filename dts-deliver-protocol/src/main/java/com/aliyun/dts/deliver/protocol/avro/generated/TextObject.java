/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.aliyun.dts.deliver.protocol.avro.generated;

import org.apache.avro.specific.SpecificData;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class TextObject extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = -5380090807502256827L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"TextObject\",\"namespace\":\"com.aliyun.dts.deliver.protocol.avro.generated\",\"fields\":[{\"name\":\"type\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"value\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<TextObject> ENCODER =
      new BinaryMessageEncoder<TextObject>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<TextObject> DECODER =
      new BinaryMessageDecoder<TextObject>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   */
  public static BinaryMessageDecoder<TextObject> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   */
  public static BinaryMessageDecoder<TextObject> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<TextObject>(MODEL$, SCHEMA$, resolver);
  }

  /** Serializes this TextObject to a ByteBuffer. */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /** Deserializes a TextObject from a ByteBuffer. */
  public static TextObject fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  @Deprecated public java.lang.String type;
  @Deprecated public java.lang.String value;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public TextObject() {}

  /**
   * All-args constructor.
   * @param type The new value for type
   * @param value The new value for value
   */
  public TextObject(java.lang.String type, java.lang.String value) {
    this.type = type;
    this.value = value;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return type;
    case 1: return value;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: type = (java.lang.String)value$; break;
    case 1: value = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'type' field.
   * @return The value of the 'type' field.
   */
  public java.lang.String getType() {
    return type;
  }

  /**
   * Sets the value of the 'type' field.
   * @param value the value to set.
   */
  public void setType(java.lang.String value) {
    this.type = value;
  }

  /**
   * Gets the value of the 'value' field.
   * @return The value of the 'value' field.
   */
  public java.lang.String getValue() {
    return value;
  }

  /**
   * Sets the value of the 'value' field.
   * @param value the value to set.
   */
  public void setValue(java.lang.String value) {
    this.value = value;
  }

  /**
   * Creates a new TextObject RecordBuilder.
   * @return A new TextObject RecordBuilder
   */
  public static com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder newBuilder() {
    return new com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder();
  }

  /**
   * Creates a new TextObject RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new TextObject RecordBuilder
   */
  public static com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder newBuilder(com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder other) {
    return new com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder(other);
  }

  /**
   * Creates a new TextObject RecordBuilder by copying an existing TextObject instance.
   * @param other The existing instance to copy.
   * @return A new TextObject RecordBuilder
   */
  public static com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder newBuilder(com.aliyun.dts.deliver.protocol.avro.generated.TextObject other) {
    return new com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder(other);
  }

  /**
   * RecordBuilder for TextObject instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<TextObject>
    implements org.apache.avro.data.RecordBuilder<TextObject> {

    private java.lang.String type;
    private java.lang.String value;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.value)) {
        this.value = data().deepCopy(fields()[1].schema(), other.value);
        fieldSetFlags()[1] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing TextObject instance
     * @param other The existing instance to copy.
     */
    private Builder(com.aliyun.dts.deliver.protocol.avro.generated.TextObject other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.type)) {
        this.type = data().deepCopy(fields()[0].schema(), other.type);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.value)) {
        this.value = data().deepCopy(fields()[1].schema(), other.value);
        fieldSetFlags()[1] = true;
      }
    }

    /**
      * Gets the value of the 'type' field.
      * @return The value.
      */
    public java.lang.String getType() {
      return type;
    }

    /**
      * Sets the value of the 'type' field.
      * @param value The value of 'type'.
      * @return This builder.
      */
    public com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder setType(java.lang.String value) {
      validate(fields()[0], value);
      this.type = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'type' field has been set.
      * @return True if the 'type' field has been set, false otherwise.
      */
    public boolean hasType() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'type' field.
      * @return This builder.
      */
    public com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder clearType() {
      type = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'value' field.
      * @return The value.
      */
    public java.lang.String getValue() {
      return value;
    }

    /**
      * Sets the value of the 'value' field.
      * @param value The value of 'value'.
      * @return This builder.
      */
    public com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder setValue(java.lang.String value) {
      validate(fields()[1], value);
      this.value = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'value' field has been set.
      * @return True if the 'value' field has been set, false otherwise.
      */
    public boolean hasValue() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'value' field.
      * @return This builder.
      */
    public com.aliyun.dts.deliver.protocol.avro.generated.TextObject.Builder clearValue() {
      value = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public TextObject build() {
      try {
        TextObject record = new TextObject();
        record.type = fieldSetFlags()[0] ? this.type : (java.lang.String) defaultValue(fields()[0]);
        record.value = fieldSetFlags()[1] ? this.value : (java.lang.String) defaultValue(fields()[1]);
        return record;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<TextObject>
    WRITER$ = (org.apache.avro.io.DatumWriter<TextObject>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<TextObject>
    READER$ = (org.apache.avro.io.DatumReader<TextObject>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
