package com.aliyun.dts.deliver.protocol.record;


import com.aliyun.dts.deliver.commons.util.NullableOptional;
import com.aliyun.dts.deliver.protocol.record.value.Value;

import java.security.InvalidParameterException;
import java.util.Set;

public interface RecordField {
    /**
     * Get the field name, which is case sensitive.
     */
    String getFieldName();

    /**
     * Get alias names of this field.
     */
    Set<String> getAliases();

    /**
     * Get raw data type of this field.
     */
    RawDataType getRawDataType();

    /**
     * Get raw data type of source for this field. If there is no source, the behavior is same with getRawDataType.
     */
    RawDataType getSourceRawDataType();

    /**
     * Set raw data type of source for this field.
     */
    void setSourceRawDataType(RawDataType rawDataType);

    /**
     * Get default value of current field.
     */
    Value getDefaultValue();

    /**
     * Generate dynamic value base on whole record columns.
     */
    default NullableOptional<Value> generateDynamicValue(Record record, boolean forBeforeImage) {
        throw new InvalidParameterException("Not support it");
    }

    /**
     * Determine if current field is nullable.
     */
    boolean isNullable();

    /**
     * Determine if current field is an element of uk.
     */
    boolean isUnique();

    /**
     * set if current field is an element of uk.
     */
    RecordField setUnique(boolean unique);

    /**
     * Determine if current field is an element of pk.
     */
    boolean isPrimary();

    /**
     * Determine if current field is an element of some index.
     */
    boolean isIndexed();

    /**
     * Determine if current field is auto incremental.
     */
    boolean isAutoIncrement();

    /**
     * Determine if current field is auto generated by other columns.
     */
    default boolean isGenerated() {
        return false;
    }

    /**
     * Determine if current filed is a dynamic column.
     * For such type column, framework can NOT mark it ignored or remove it,
     * and data is provided by concrete processors.
     */
    default boolean isDynamic() {
        return false;
    }

    /**
     * Get current relative position in index. for example, pk is composed by (field1, field2),
     * so the field1 keySeq is 0, the field2 keySeq is 1.
     */
    int keySeq();

    /**
     * Get field position to set/get value, which starts from 0.
     */
    int getFieldPosition();

    /**
     * Set field position to set/get value, which starts from 0.
     */
    void setFieldPosition(int position);

    /**
     * Get the scale for current field.
     */
    int getScale();

    /**
     * Get the precision for current field.
     */
    default int getPrecision() {
        return 0;
    }

    default String getOriginalColumnTypeName() {
        return null;
    }

    default int getOriginalColumnTypeNumber() {
        return 0;
    }

    int getDisplaySize();

    default void resetAlias(String alias) {
    }

    default boolean isReadOnly() {
        return false;
    }

    default String getEncoding() {
        return "utf8";
    }
}

