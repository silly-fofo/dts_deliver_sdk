package com.aliyun.dts.deliver.protocol.record;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class RecordIndexInfo {

    public enum IndexType {
        PrimaryKey,
        UniqueKey,
        NormalIndex,
        ForeignKey,
        Unknown
    }

    private IndexType indexType;
    private List<RecordField> indexFields;
    private long cardinality;
    private boolean nullable = true;
    private boolean isFirstUniqueIndex = false;
    private String name;

    public RecordIndexInfo(IndexType type) {
        this.indexType = type;
        this.indexFields = new ArrayList<>();
    }

    public void addField(RecordField recordField) {
        for (RecordField field : indexFields) {
            if (StringUtils.equals(field.getFieldName(), recordField.getFieldName())) {
                return;
            }
        }
        indexFields.add(recordField);
    }

    public void removeField(RecordField recordField) {
        indexFields.remove(recordField);
    }

    public synchronized List<RecordField> getIndexFields() {
        return indexFields;
    }

    public IndexType getIndexType() {
        return indexType;
    }

    public boolean isPrimaryKeyIndex() {
        return IndexType.PrimaryKey == indexType;
    }

    public boolean isUniqueKeyIndex() {
        return IndexType.UniqueKey == indexType;
    }

    public boolean isNormalIndex() {
        return IndexType.NormalIndex == indexType;
    }

    public long getCardinality() {
        return cardinality;
    }

    public void setCardinality(long cardinality) {
        this.cardinality = cardinality;
    }

    public boolean isNullable() {
        return nullable;
    }

    public void setNullable(boolean nullable) {
        this.nullable = nullable;
    }

    public boolean isFirstUniqueIndex() {
        return isFirstUniqueIndex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFirstUniqueIndex(boolean firstUniqueIndex) {
        isFirstUniqueIndex = firstUniqueIndex;
    }

    @Override
    public String toString() {
        return indexType
            + (StringUtils.isEmpty(name) ? "" : name)
            + " ("
            + indexFields.stream()
                .map(RecordField::getFieldName)
                .reduce("",
                    (f1, f2) -> {
                        if (StringUtils.isEmpty(f1)) {
                            return f2;
                        }
                        return f1 + ", " + f2;
                    })
            + ")";
    }
}
