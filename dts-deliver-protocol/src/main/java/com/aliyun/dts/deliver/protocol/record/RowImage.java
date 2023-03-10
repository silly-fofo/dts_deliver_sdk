package com.aliyun.dts.deliver.protocol.record;


import com.aliyun.dts.deliver.commons.util.NullableOptional;
import com.aliyun.dts.deliver.protocol.record.value.Value;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Map;
import java.util.function.Function;

public interface RowImage {

    /**
     * <p>
     * Returns a view of the the values of the fields in this Record. Note that this method returns values only for
     * those entries in the Record's schema. This allows the Record to guarantee that it will return the values in
     * the order dictated by the schema.
     * </p>
     *
     * <b>NOTE:</b> The array that is returned may be an underlying array that is backing
     * the contents of the Record. As such, modifying the array in any way may result in
     * modifying the record.
     */
    Value[] getValues();

    /**
     * Return the value of specified @pos.
     */
    Value getValue(int pos);

    /**
     * Return the Optional of value of specified @fieldName.
     * This method is different as other getValue, for the field matched @fileName should never exist.
     */
    NullableOptional<Value> getValue(String fieldName);

    /**
     * Return the value of specified @recordField.
     */
    Value getValue(RecordField recordField);

    /**
     * Return the Optional of value of specified @fieldName.
     * This method is different as other getValue, for the field matched @fileName should never exist.
     */
    default NullableOptional<Value> getValueIgnoreCase(String fieldName) {
        return getValue(fieldName);
    }

    /**
     * Return the primary keys of current row image.
     */
    Pair<RecordField, Value>[] getPrimaryKeyValues();

    /**
     * Return the merged field and value pairs fo all unique keys in current record.
     */
    Pair<RecordField, Value>[] getUniqueKeyValues();

    /**
     * Return the foreign keys of current row image.
     */
    Pair<RecordField, Value>[] getForeignKeyValues();

    /**
     * Converts the Record into a Map whose keys are the same as the Record's field names and the values are the field values
     *
     * @return a Map that represents the values in the Record
     */
    Map<String, Value> toMap(Function<String, String> filedNameResolver, Function<Value, Value> valueResolver);

    /**
     * The total size of all values in current row image.
     */
    long size();

    /**
     * Get the record schema that describes current row image.
     */
    RecordSchema getRecordSchema();
}
