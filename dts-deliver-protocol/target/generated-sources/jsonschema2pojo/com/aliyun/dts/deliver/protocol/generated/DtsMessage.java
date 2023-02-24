
package com.aliyun.dts.deliver.protocol.generated;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "log",
    "spec",
    "connectionStatus",
    "catalog",
    "record",
    "state",
    "trace",
    "control",
    "group_key",
    "size"
})
@Generated("jsonschema2pojo")
public class DtsMessage {

    /**
     * Message type
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("Message type")
    private DtsMessage.Type type;
    @JsonProperty("log")
    private DtsLogMessage log;
    /**
     * Specification of a connector (source/destination)
     * 
     */
    @JsonProperty("spec")
    @JsonPropertyDescription("Specification of a connector (source/destination)")
    private ConnectorSpecification spec;
    /**
     * Dts connection status
     * 
     */
    @JsonProperty("connectionStatus")
    @JsonPropertyDescription("Dts connection status")
    private DtsConnectionStatus connectionStatus;
    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("catalog")
    @JsonPropertyDescription("Dts stream schema catalog")
    private DtsCatalog catalog;
    @JsonProperty("record")
    private DtsRecordMessage record;
    @JsonProperty("state")
    private DtsStateMessage state;
    @JsonProperty("trace")
    private DtsTraceMessage trace;
    @JsonProperty("control")
    private DtsControlMessage control;
    /**
     * how the data be grouped, when sink to target, such as hash(db_name + tab_name).
     * 
     */
    @JsonProperty("group_key")
    @JsonPropertyDescription("how the data be grouped, when sink to target, such as hash(db_name + tab_name).")
    private Long groupKey;
    /**
     * message size
     * 
     */
    @JsonProperty("size")
    @JsonPropertyDescription("message size")
    private Long size;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * Message type
     * (Required)
     * 
     */
    @JsonProperty("type")
    public DtsMessage.Type getType() {
        return type;
    }

    /**
     * Message type
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(DtsMessage.Type type) {
        this.type = type;
    }

    public DtsMessage withType(DtsMessage.Type type) {
        this.type = type;
        return this;
    }

    @JsonProperty("log")
    public DtsLogMessage getLog() {
        return log;
    }

    @JsonProperty("log")
    public void setLog(DtsLogMessage log) {
        this.log = log;
    }

    public DtsMessage withLog(DtsLogMessage log) {
        this.log = log;
        return this;
    }

    /**
     * Specification of a connector (source/destination)
     * 
     */
    @JsonProperty("spec")
    public ConnectorSpecification getSpec() {
        return spec;
    }

    /**
     * Specification of a connector (source/destination)
     * 
     */
    @JsonProperty("spec")
    public void setSpec(ConnectorSpecification spec) {
        this.spec = spec;
    }

    public DtsMessage withSpec(ConnectorSpecification spec) {
        this.spec = spec;
        return this;
    }

    /**
     * Dts connection status
     * 
     */
    @JsonProperty("connectionStatus")
    public DtsConnectionStatus getConnectionStatus() {
        return connectionStatus;
    }

    /**
     * Dts connection status
     * 
     */
    @JsonProperty("connectionStatus")
    public void setConnectionStatus(DtsConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
    }

    public DtsMessage withConnectionStatus(DtsConnectionStatus connectionStatus) {
        this.connectionStatus = connectionStatus;
        return this;
    }

    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("catalog")
    public DtsCatalog getCatalog() {
        return catalog;
    }

    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("catalog")
    public void setCatalog(DtsCatalog catalog) {
        this.catalog = catalog;
    }

    public DtsMessage withCatalog(DtsCatalog catalog) {
        this.catalog = catalog;
        return this;
    }

    @JsonProperty("record")
    public DtsRecordMessage getRecord() {
        return record;
    }

    @JsonProperty("record")
    public void setRecord(DtsRecordMessage record) {
        this.record = record;
    }

    public DtsMessage withRecord(DtsRecordMessage record) {
        this.record = record;
        return this;
    }

    @JsonProperty("state")
    public DtsStateMessage getState() {
        return state;
    }

    @JsonProperty("state")
    public void setState(DtsStateMessage state) {
        this.state = state;
    }

    public DtsMessage withState(DtsStateMessage state) {
        this.state = state;
        return this;
    }

    @JsonProperty("trace")
    public DtsTraceMessage getTrace() {
        return trace;
    }

    @JsonProperty("trace")
    public void setTrace(DtsTraceMessage trace) {
        this.trace = trace;
    }

    public DtsMessage withTrace(DtsTraceMessage trace) {
        this.trace = trace;
        return this;
    }

    @JsonProperty("control")
    public DtsControlMessage getControl() {
        return control;
    }

    @JsonProperty("control")
    public void setControl(DtsControlMessage control) {
        this.control = control;
    }

    public DtsMessage withControl(DtsControlMessage control) {
        this.control = control;
        return this;
    }

    /**
     * how the data be grouped, when sink to target, such as hash(db_name + tab_name).
     * 
     */
    @JsonProperty("group_key")
    public Long getGroupKey() {
        return groupKey;
    }

    /**
     * how the data be grouped, when sink to target, such as hash(db_name + tab_name).
     * 
     */
    @JsonProperty("group_key")
    public void setGroupKey(Long groupKey) {
        this.groupKey = groupKey;
    }

    public DtsMessage withGroupKey(Long groupKey) {
        this.groupKey = groupKey;
        return this;
    }

    /**
     * message size
     * 
     */
    @JsonProperty("size")
    public Long getSize() {
        return size;
    }

    /**
     * message size
     * 
     */
    @JsonProperty("size")
    public void setSize(Long size) {
        this.size = size;
    }

    public DtsMessage withSize(Long size) {
        this.size = size;
        return this;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    public DtsMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("log");
        sb.append('=');
        sb.append(((this.log == null)?"<null>":this.log));
        sb.append(',');
        sb.append("spec");
        sb.append('=');
        sb.append(((this.spec == null)?"<null>":this.spec));
        sb.append(',');
        sb.append("connectionStatus");
        sb.append('=');
        sb.append(((this.connectionStatus == null)?"<null>":this.connectionStatus));
        sb.append(',');
        sb.append("catalog");
        sb.append('=');
        sb.append(((this.catalog == null)?"<null>":this.catalog));
        sb.append(',');
        sb.append("record");
        sb.append('=');
        sb.append(((this.record == null)?"<null>":this.record));
        sb.append(',');
        sb.append("state");
        sb.append('=');
        sb.append(((this.state == null)?"<null>":this.state));
        sb.append(',');
        sb.append("trace");
        sb.append('=');
        sb.append(((this.trace == null)?"<null>":this.trace));
        sb.append(',');
        sb.append("control");
        sb.append('=');
        sb.append(((this.control == null)?"<null>":this.control));
        sb.append(',');
        sb.append("groupKey");
        sb.append('=');
        sb.append(((this.groupKey == null)?"<null>":this.groupKey));
        sb.append(',');
        sb.append("size");
        sb.append('=');
        sb.append(((this.size == null)?"<null>":this.size));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.log == null)? 0 :this.log.hashCode()));
        result = ((result* 31)+((this.catalog == null)? 0 :this.catalog.hashCode()));
        result = ((result* 31)+((this.control == null)? 0 :this.control.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.spec == null)? 0 :this.spec.hashCode()));
        result = ((result* 31)+((this.groupKey == null)? 0 :this.groupKey.hashCode()));
        result = ((result* 31)+((this.trace == null)? 0 :this.trace.hashCode()));
        result = ((result* 31)+((this.size == null)? 0 :this.size.hashCode()));
        result = ((result* 31)+((this.record == null)? 0 :this.record.hashCode()));
        result = ((result* 31)+((this.connectionStatus == null)? 0 :this.connectionStatus.hashCode()));
        result = ((result* 31)+((this.state == null)? 0 :this.state.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsMessage) == false) {
            return false;
        }
        DtsMessage rhs = ((DtsMessage) other);
        return (((((((((((((this.log == rhs.log)||((this.log!= null)&&this.log.equals(rhs.log)))&&((this.catalog == rhs.catalog)||((this.catalog!= null)&&this.catalog.equals(rhs.catalog))))&&((this.control == rhs.control)||((this.control!= null)&&this.control.equals(rhs.control))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.spec == rhs.spec)||((this.spec!= null)&&this.spec.equals(rhs.spec))))&&((this.groupKey == rhs.groupKey)||((this.groupKey!= null)&&this.groupKey.equals(rhs.groupKey))))&&((this.trace == rhs.trace)||((this.trace!= null)&&this.trace.equals(rhs.trace))))&&((this.size == rhs.size)||((this.size!= null)&&this.size.equals(rhs.size))))&&((this.record == rhs.record)||((this.record!= null)&&this.record.equals(rhs.record))))&&((this.connectionStatus == rhs.connectionStatus)||((this.connectionStatus!= null)&&this.connectionStatus.equals(rhs.connectionStatus))))&&((this.state == rhs.state)||((this.state!= null)&&this.state.equals(rhs.state))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }


    /**
     * Message type
     * 
     */
    @Generated("jsonschema2pojo")
    public enum Type {

        RECORD("RECORD"),
        STATE("STATE"),
        LOG("LOG"),
        SPEC("SPEC"),
        CONNECTION_STATUS("CONNECTION_STATUS"),
        CATALOG("CATALOG"),
        TRACE("TRACE"),
        CONTROL("CONTROL");
        private final String value;
        private final static Map<String, DtsMessage.Type> CONSTANTS = new HashMap<String, DtsMessage.Type>();

        static {
            for (DtsMessage.Type c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Type(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return this.value;
        }

        @JsonValue
        public String value() {
            return this.value;
        }

        @JsonCreator
        public static DtsMessage.Type fromValue(String value) {
            DtsMessage.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
