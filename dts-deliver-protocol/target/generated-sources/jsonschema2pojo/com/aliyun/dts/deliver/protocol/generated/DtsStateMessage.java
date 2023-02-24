
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
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "type",
    "stream",
    "global",
    "data"
})
@Generated("jsonschema2pojo")
public class DtsStateMessage {

    /**
     * The type of state the other fields represent. Is set to LEGACY, the state data should be read from the `data` field for backwards compatibility. If not set, assume the state object is type LEGACY. GLOBAL means that the state should be read from `global` and means that it represents the state for all the streams. It contains one shared state and individual stream states. PER_STREAM means that the state should be read from `stream`. The state present in this field correspond to the isolated state of the associated stream description.
     * 
     * 
     */
    @JsonProperty("type")
    @JsonPropertyDescription("The type of state the other fields represent. Is set to LEGACY, the state data should be read from the `data` field for backwards compatibility. If not set, assume the state object is type LEGACY. GLOBAL means that the state should be read from `global` and means that it represents the state for all the streams. It contains one shared state and individual stream states. PER_STREAM means that the state should be read from `stream`. The state present in this field correspond to the isolated state of the associated stream description.\n")
    private DtsStateMessage.DtsStateType type;
    @JsonProperty("stream")
    private DtsStreamState stream;
    @JsonProperty("global")
    private DtsGlobalState global;
    /**
     * (Deprecated) the state data
     * 
     */
    @JsonProperty("data")
    @JsonPropertyDescription("(Deprecated) the state data")
    private JsonNode data;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * The type of state the other fields represent. Is set to LEGACY, the state data should be read from the `data` field for backwards compatibility. If not set, assume the state object is type LEGACY. GLOBAL means that the state should be read from `global` and means that it represents the state for all the streams. It contains one shared state and individual stream states. PER_STREAM means that the state should be read from `stream`. The state present in this field correspond to the isolated state of the associated stream description.
     * 
     * 
     */
    @JsonProperty("type")
    public DtsStateMessage.DtsStateType getType() {
        return type;
    }

    /**
     * The type of state the other fields represent. Is set to LEGACY, the state data should be read from the `data` field for backwards compatibility. If not set, assume the state object is type LEGACY. GLOBAL means that the state should be read from `global` and means that it represents the state for all the streams. It contains one shared state and individual stream states. PER_STREAM means that the state should be read from `stream`. The state present in this field correspond to the isolated state of the associated stream description.
     * 
     * 
     */
    @JsonProperty("type")
    public void setType(DtsStateMessage.DtsStateType type) {
        this.type = type;
    }

    public DtsStateMessage withType(DtsStateMessage.DtsStateType type) {
        this.type = type;
        return this;
    }

    @JsonProperty("stream")
    public DtsStreamState getStream() {
        return stream;
    }

    @JsonProperty("stream")
    public void setStream(DtsStreamState stream) {
        this.stream = stream;
    }

    public DtsStateMessage withStream(DtsStreamState stream) {
        this.stream = stream;
        return this;
    }

    @JsonProperty("global")
    public DtsGlobalState getGlobal() {
        return global;
    }

    @JsonProperty("global")
    public void setGlobal(DtsGlobalState global) {
        this.global = global;
    }

    public DtsStateMessage withGlobal(DtsGlobalState global) {
        this.global = global;
        return this;
    }

    /**
     * (Deprecated) the state data
     * 
     */
    @JsonProperty("data")
    public JsonNode getData() {
        return data;
    }

    /**
     * (Deprecated) the state data
     * 
     */
    @JsonProperty("data")
    public void setData(JsonNode data) {
        this.data = data;
    }

    public DtsStateMessage withData(JsonNode data) {
        this.data = data;
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

    public DtsStateMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsStateMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("type");
        sb.append('=');
        sb.append(((this.type == null)?"<null>":this.type));
        sb.append(',');
        sb.append("stream");
        sb.append('=');
        sb.append(((this.stream == null)?"<null>":this.stream));
        sb.append(',');
        sb.append("global");
        sb.append('=');
        sb.append(((this.global == null)?"<null>":this.global));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
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
        result = ((result* 31)+((this.global == null)? 0 :this.global.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.type == null)? 0 :this.type.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.stream == null)? 0 :this.stream.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsStateMessage) == false) {
            return false;
        }
        DtsStateMessage rhs = ((DtsStateMessage) other);
        return ((((((this.global == rhs.global)||((this.global!= null)&&this.global.equals(rhs.global)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.type == rhs.type)||((this.type!= null)&&this.type.equals(rhs.type))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.stream == rhs.stream)||((this.stream!= null)&&this.stream.equals(rhs.stream))));
    }


    /**
     * The type of state the other fields represent. Is set to LEGACY, the state data should be read from the `data` field for backwards compatibility. If not set, assume the state object is type LEGACY. GLOBAL means that the state should be read from `global` and means that it represents the state for all the streams. It contains one shared state and individual stream states. PER_STREAM means that the state should be read from `stream`. The state present in this field correspond to the isolated state of the associated stream description.
     * 
     * 
     */
    @Generated("jsonschema2pojo")
    public enum DtsStateType {

        GLOBAL("GLOBAL"),
        STREAM("STREAM"),
        LEGACY("LEGACY");
        private final String value;
        private final static Map<String, DtsStateMessage.DtsStateType> CONSTANTS = new HashMap<String, DtsStateMessage.DtsStateType>();

        static {
            for (DtsStateMessage.DtsStateType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        DtsStateType(String value) {
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
        public static DtsStateMessage.DtsStateType fromValue(String value) {
            DtsStateMessage.DtsStateType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
