
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
    "emitted_at",
    "connectorConfig"
})
@Generated("jsonschema2pojo")
public class DtsControlMessage {

    /**
     * orchestrator type
     * <p>
     * the type of orchestrator message
     * (Required)
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("the type of orchestrator message")
    private DtsControlMessage.Type type;
    /**
     * the time in ms that the message was emitted
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    @JsonPropertyDescription("the time in ms that the message was emitted")
    private Double emittedAt;
    @JsonProperty("connectorConfig")
    private DtsControlConnectorConfigMessage connectorConfig;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * orchestrator type
     * <p>
     * the type of orchestrator message
     * (Required)
     * 
     */
    @JsonProperty("type")
    public DtsControlMessage.Type getType() {
        return type;
    }

    /**
     * orchestrator type
     * <p>
     * the type of orchestrator message
     * (Required)
     * 
     */
    @JsonProperty("type")
    public void setType(DtsControlMessage.Type type) {
        this.type = type;
    }

    public DtsControlMessage withType(DtsControlMessage.Type type) {
        this.type = type;
        return this;
    }

    /**
     * the time in ms that the message was emitted
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    public Double getEmittedAt() {
        return emittedAt;
    }

    /**
     * the time in ms that the message was emitted
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    public void setEmittedAt(Double emittedAt) {
        this.emittedAt = emittedAt;
    }

    public DtsControlMessage withEmittedAt(Double emittedAt) {
        this.emittedAt = emittedAt;
        return this;
    }

    @JsonProperty("connectorConfig")
    public DtsControlConnectorConfigMessage getConnectorConfig() {
        return connectorConfig;
    }

    @JsonProperty("connectorConfig")
    public void setConnectorConfig(DtsControlConnectorConfigMessage connectorConfig) {
        this.connectorConfig = connectorConfig;
    }

    public DtsControlMessage withConnectorConfig(DtsControlConnectorConfigMessage connectorConfig) {
        this.connectorConfig = connectorConfig;
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

    public DtsControlMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsControlMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("emittedAt");
        sb.append('=');
        sb.append(((this.emittedAt == null)?"<null>":this.emittedAt));
        sb.append(',');
        sb.append("connectorConfig");
        sb.append('=');
        sb.append(((this.connectorConfig == null)?"<null>":this.connectorConfig));
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
        result = ((result* 31)+((this.connectorConfig == null)? 0 :this.connectorConfig.hashCode()));
        result = ((result* 31)+((this.emittedAt == null)? 0 :this.emittedAt.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsControlMessage) == false) {
            return false;
        }
        DtsControlMessage rhs = ((DtsControlMessage) other);
        return (((((this.connectorConfig == rhs.connectorConfig)||((this.connectorConfig!= null)&&this.connectorConfig.equals(rhs.connectorConfig)))&&((this.emittedAt == rhs.emittedAt)||((this.emittedAt!= null)&&this.emittedAt.equals(rhs.emittedAt))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))));
    }


    /**
     * orchestrator type
     * <p>
     * the type of orchestrator message
     * 
     */
    @Generated("jsonschema2pojo")
    public enum Type {

        CONNECTOR_CONFIG("CONNECTOR_CONFIG");
        private final String value;
        private final static Map<String, DtsControlMessage.Type> CONSTANTS = new HashMap<String, DtsControlMessage.Type>();

        static {
            for (DtsControlMessage.Type c: values()) {
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
        public static DtsControlMessage.Type fromValue(String value) {
            DtsControlMessage.Type constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
