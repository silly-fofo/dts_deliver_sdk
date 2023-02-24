package com.aliyun.dts.deliver.test;

import com.aliyun.dts.deliver.base.Source;
import com.aliyun.dts.deliver.commons.concurrency.VoidCallable;
import com.aliyun.dts.deliver.commons.config.Settings;
import com.aliyun.dts.deliver.commons.util.AutoCloseableIterator;
import com.aliyun.dts.deliver.commons.util.DefaultAutoCloseableIterator;
import com.aliyun.dts.deliver.protocol.generated.*;
import com.aliyun.dts.deliver.protocol.record.OperationType;
import com.aliyun.dts.deliver.protocol.record.Record;
import com.aliyun.dts.deliver.test.util.DtsRecordTestUtil;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class FakeSource implements Source {

    public static final String DB_NAME1 = "dts_db1";
    public static final String TABLE_NAME1 = "dts_tab1";
    public static final int GROUP_KEY1 = 100;

    public static final String DB_NAME2 = "dts_db2";
    public static final String TABLE_NAME2 = "dts_tab2";
    public static final int GROUP_KEY2 = 200;

    protected static final String DB_NAME3 = "dts_db3";
    protected static final String TABLE_NAME3 = "dts_tab3";
    protected static final int GROUP_KEY3 = 300;

    @Override
    public DtsCatalog discover(JsonNode config) throws Exception {
        return null;
    }

    @Override
    public AutoCloseableIterator<DtsMessage> read(Settings settings, ConfiguredDtsCatalog catalog, JsonNode state) throws Exception {
        long timestamp = 1457496970;

        Record record1 = DtsRecordTestUtil.createRecord(DB_NAME3, TABLE_NAME3, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        Record record2 = DtsRecordTestUtil.createRecord(DB_NAME3, TABLE_NAME3, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        Record record3 = DtsRecordTestUtil.createRecord(DB_NAME3, TABLE_NAME3, OperationType.INSERT, String.valueOf(timestamp),
                DtsRecordTestUtil.createField("id1", "1", null, true, false),
                DtsRecordTestUtil.createField("id2", "2", "uk1", false, false),
                DtsRecordTestUtil.createField("id3", "3", "uk2", false, false));

        List<DtsMessage> dtsMessageList = new ArrayList<>();
        dtsMessageList.add(new DtsMessage().withType(DtsMessage.Type.RECORD).withRecord(new DtsRecordMessage().withData(record1)));

        return new DefaultAutoCloseableIterator(dtsMessageList.iterator(), VoidCallable.NOOP);
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
