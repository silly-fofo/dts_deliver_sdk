package com.aliyun.dts.deliver.test;

import com.aliyun.dts.deliver.DtsMessageInterceptor;
import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.concurrency.VoidCallable;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.util.AutoCloseableIterator;
import com.aliyun.dts.deliver.commons.util.DefaultAutoCloseableIterator;
import com.aliyun.dts.deliver.protocol.generated.*;
import com.aliyun.dts.deliver.protocol.record.OperationType;
import com.aliyun.dts.deliver.protocol.record.Record;
import com.aliyun.dts.deliver.recordinterceptor.dmlfilter.FilterRecordInterceptor;
import com.aliyun.dts.deliver.test.util.DtsRecordTestUtil;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.kafka.common.metrics.Metrics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class FakeSource implements Source {

    public static final String DB_NAME1 = "dts_db1";
    public static final String TABLE_NAME1 = "dts_tab1";
    public static final long GROUP_KEY1 = 100;

    public static final String DB_NAME2 = "dts_db2";
    public static final String TABLE_NAME2 = "dts_tab2";
    public static final long GROUP_KEY2 = 200;

    protected static final String DB_NAME3 = "dts_db3";
    protected static final String TABLE_NAME3 = "dts_tab3";
    protected static final long GROUP_KEY3 = 300;

    private String name;
    private Settings settings;

    public FakeSource(String name, Settings settings) {
        this.name = name;
        this.settings = settings;
    }

    @Override
    public DtsCatalog discover(JsonNode config) throws Exception {
        return null;
    }

    @Override
    public AutoCloseableIterator<DtsMessage> read(Settings settings, ConfiguredDtsCatalog catalog, JsonNode state) throws Exception {
        long timestamp = 1457496970;

        Record record1 = DtsRecordTestUtil.createRecord(DB_NAME1, TABLE_NAME1, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        Record record2 = DtsRecordTestUtil.createRecord(DB_NAME2, TABLE_NAME2, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        Record record3 = DtsRecordTestUtil.createRecord(DB_NAME3, TABLE_NAME3, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        List<DtsMessage> dtsMessageList = new ArrayList<>();
        dtsMessageList.add(new DtsMessage().withType(DtsMessage.Type.RECORD).withGroupKey(GROUP_KEY1).withRecord(record1));
        dtsMessageList.add(new DtsMessage().withType(DtsMessage.Type.RECORD).withGroupKey(GROUP_KEY2).withRecord(record2));
        dtsMessageList.add(new DtsMessage().withType(DtsMessage.Type.RECORD).withGroupKey(GROUP_KEY3).withRecord(record3));

        return new DefaultAutoCloseableIterator(dtsMessageList.iterator(), VoidCallable.NOOP);
    }

    @Override
    public List<DtsMessageInterceptor> recordInterceptors(Metrics metrics) {
        List<DtsMessageInterceptor> recordInterceptors = new LinkedList<>();

        // filter record interceptor is always enabled
        recordInterceptors.add(new FilterRecordInterceptor(settings, metrics));
        return Collections.emptyList();
    }

    @Override
    public ConnectorSpecification spec() throws Exception {
        return null;
    }

    @Override
    public DtsConnectionStatus check(JsonNode config) throws Exception {
        return null;
    }

    @Override
    public void close() {

    }


}
