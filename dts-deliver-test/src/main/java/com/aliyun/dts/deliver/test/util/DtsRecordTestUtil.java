package com.aliyun.dts.deliver.test.util;

import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.protocol.record.*;
import com.aliyun.dts.deliver.protocol.record.impl.*;
import com.aliyun.dts.deliver.protocol.record.value.StringValue;
import com.aliyun.dts.deliver.protocol.record.value.Value;
import com.google.common.base.Preconditions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class DtsRecordTestUtil {
    private static RawDataType defaultRawDataType =  DefaultRawDataType.of("varchar", 7, false);

    public static Pair<Pair<String, RawDataType>, Pair<Value, Value>> buildField(String filedName, RawDataType rawDataType, Value before, Value after) {
        return Pair.of(Pair.of(filedName, rawDataType), Pair.of(before, after));
    }

    public static DefaultRecord createRecord(String dbName, String tableName, OperationType opType, String timestamp, MockValue... fields) {
        if (opType == OperationType.DDL) {
            RecordSchema recordSchema = new DefaultRecordSchema(dbName, dbName, tableName,
                    Collections.singletonList(DefaultRecordField.builder().withFieldName("ddl").withRawDataType(DefaultRawDataType.of("ddl", 0)).get()));
            DefaultRecord ret = new MockRecord(opType, recordSchema, Long.valueOf(timestamp));
            DefaultRowImage defaultRowImage = new DefaultRowImage(recordSchema);
            defaultRowImage.setValue("ddl", fields[0]);
            ret.setAfterImage(defaultRowImage);
            return ret;
        } else if (opType == OperationType.HEARTBEAT || opType == OperationType.BEGIN || opType == OperationType.COMMIT) {
            DefaultRecord ret = new MockRecord(opType, null, Long.valueOf(timestamp));
            return ret;
        }
        int exceptColumnCount = opType == OperationType.UPDATE ? fields.length / 2 : fields.length;
        Map<String, MockValue> fieldNames = new HashMap<>();
        List<MockValue> oldValue = new LinkedList<>();
        List<MockValue> newValue = new LinkedList<>();
        Set<String> primaryKeyNames = new HashSet<>();
        Map<String, Set<String>> ukNames = new HashMap<>();
        for (MockValue mockValue : fields) {
            if (mockValue.isPK()) {
                primaryKeyNames.add(mockValue.getName());
            }
            if (mockValue.isBeforeImage()) {
                oldValue.add(mockValue);
            } else {
                newValue.add(mockValue);
            }
            fieldNames.put(mockValue.getName(), mockValue);
            if (null != mockValue.getIndexName()) {
                ukNames.compute(mockValue.getIndexName(), (k, v) -> {
                    if (null == v) {
                        v = new HashSet<>();
                    }
                    v.add(mockValue.getName());
                    return v;
                });
            }
        }

        Preconditions.checkArgument(exceptColumnCount == fieldNames.size());
        switch (opType) {
            case UPDATE: {
                Preconditions.checkArgument(oldValue.size() == newValue.size());
                break;
            }
            case DELETE: {
                Preconditions.checkArgument(newValue.size() == 0);
                break;
            }
            case INSERT: {
                Preconditions.checkArgument(oldValue.size() == 0);
                break;
            }
            default: {
            }
        }
        AtomicInteger i = new AtomicInteger(0);
        List<RecordField> recordFields = fieldNames.values().stream().map(mockValue -> DefaultRecordField.builder()
                .withFieldName(mockValue.getName()).withPrimary(mockValue.isPK())
                .withRawDataType(defaultRawDataType).withFieldPosition(i.getAndIncrement()).get()).collect(Collectors.toList());

        DefaultRecordSchema recordSchema = new DefaultRecordSchema(dbName, dbName, tableName, recordFields);
        RecordIndexInfo recordIndexInfo = new RecordIndexInfo(RecordIndexInfo.IndexType.PrimaryKey);
        primaryKeyNames.forEach(pkName -> recordIndexInfo.addField(DefaultRecordField.builder().withFieldName(pkName).withRawDataType(defaultRawDataType).get()));
        if (!CollectionUtils.isEmpty(recordIndexInfo.getIndexFields())) {
            recordSchema.setPrimaryIndexInfo(recordIndexInfo);
        } else {
            recordSchema.setPrimaryIndexInfo(null);
        }
        for (Set<String> ukNameFields : ukNames.values()) {
            RecordIndexInfo tmp = new RecordIndexInfo(RecordIndexInfo.IndexType.UniqueKey);
            for (String field : ukNameFields) {
                tmp.addField(recordSchema.getField(field).orElseThrow(() -> new RuntimeException()));
            }
            recordSchema.addUniqueIndexInfo(tmp);
        }
        DefaultRowImage beforeImage = new DefaultRowImage(recordSchema);
        DefaultRowImage afterImage = new DefaultRowImage(recordSchema);
        oldValue.forEach(v -> beforeImage.setValue(v.getName(), v));
        newValue.forEach(v -> afterImage.setValue(v.getName(), v));
        MockRecord defaultRecord = new MockRecord(opType, recordSchema, Long.valueOf(timestamp));
        defaultRecord.setAfterImage(afterImage);
        defaultRecord.setBeforeImage(beforeImage);
        return defaultRecord;
    }

    public static DefaultRecord createRecord(String dbName, String tableName, long timestamp, OperationType operationType,
                                             List<Pair<Pair<String, RawDataType>, Pair<Value, Value>>> fields, Set<String> constraints) {
        AtomicInteger i = new AtomicInteger(0);
        List<RecordField> recordFields = fields.stream().map(field -> DefaultRecordField.builder()
                .withFieldName(field.getLeft().getLeft()).withPrimary(constraints.contains(field.getLeft().getLeft()))
                .withFieldPosition(i.getAndIncrement())
                .withAliases(Collections.singleton(field.getLeft().getLeft()))
                .withRawDataType(field.getLeft().getRight()).get()).collect(Collectors.toList());

        DefaultRecordSchema recordSchema = new DefaultRecordSchema(dbName, dbName, tableName, recordFields);
        RecordIndexInfo recordIndexInfo = new RecordIndexInfo(RecordIndexInfo.IndexType.PrimaryKey);
        constraints.forEach(pkName -> recordIndexInfo.addField(DefaultRecordField.builder().withFieldName(pkName)
                .withRawDataType(
                        fields.stream()
                                .filter(v -> StringUtils.equals(v.getLeft().getLeft(), pkName))
                                .findAny()
                                .map(v -> v.getLeft().getRight())
                                .orElseThrow(() -> new RuntimeException("not find")))
                .get()));
        recordSchema.setPrimaryIndexInfo(recordIndexInfo);
        DefaultRowImage beforeImage = new DefaultRowImage(recordSchema);
        DefaultRowImage afterImage = new DefaultRowImage(recordSchema);

        fields.forEach((v) -> {
            beforeImage.setValue(v.getLeft().getLeft(), v.getRight().getLeft());
            afterImage.setValue(v.getLeft().getLeft(), v.getRight().getRight());
        });
        RecordHeader recordHeader = new RecordHeader();
        recordHeader.setSourceTimestamp(timestamp);
        DefaultRecord defaultRecord = new DefaultRecord(operationType, recordSchema, recordHeader);
        defaultRecord.setAfterImage(afterImage);
        defaultRecord.setBeforeImage(beforeImage);
        return defaultRecord;
    }

    public static DefaultRecord createRecord(String dbName, String tableName, OperationType operationType, List<Pair<Pair<String, RawDataType>, Pair<Value, Value>>> fields, Set<String> constraints) {
        return createRecord(dbName, tableName, 0, operationType, fields, constraints);
    }

    public static Record createRecord(String dbName, String tableName, OperationType operationType, List<Pair<Pair<String, RawDataType>,
            Pair<Value, Value>>> fields, Set<String> constraints, long timestamp) {
        return createRecord(dbName, tableName, timestamp, operationType, fields, constraints);
    }

    public static Record createDDLRecord(String dbName, String sql) {
        List<RecordField> fieldList = new ArrayList<RecordField>();
        fieldList.add(DefaultRecordField.builder().withFieldName("ddl").withRawDataType(DefaultRawDataType.of(0)).get());

        DefaultRecordSchema recordSchema = new DefaultRecordSchema(null, dbName, null, fieldList);

        DefaultRowImage afterImage = new DefaultRowImage(recordSchema);
        afterImage.setValue(0, new StringValue(sql));

        DefaultRecord defaultRecord = new DefaultRecord(OperationType.DDL, recordSchema, null);
        defaultRecord.setAfterImage(afterImage);
        return defaultRecord;
    }

    public static MockValue createField(String name, String value) {
        return createField(name, value, true, false);
    }

    public static MockValue createField(String name, String value, String indexName, boolean isPk, boolean isOldValue) {
        return new MockValue(name, value, indexName, isPk, isOldValue);
    }

    public static MockValue createField(String name, String value, boolean isPk, boolean isOldValue) {
        return new MockValue(name, value, null, isPk, isOldValue);
    }

}
