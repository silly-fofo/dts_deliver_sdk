
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
    "level",
    "message",
    "stack_trace"
})
@Generated("jsonschema2pojo")
public class DtsLogMessage {

    /**
     * log level
     * (Required)
     * 
     */
    @JsonProperty("level")
    @JsonPropertyDescription("log level")
    private DtsLogMessage.Level level;
    /**
     * log message
     * (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("log message")
    private String message;
    /**
     * an optional stack trace if the log message corresponds to an exception
     * 
     */
    @JsonProperty("stack_trace")
    @JsonPropertyDescription("an optional stack trace if the log message corresponds to an exception")
    private String stackTrace;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * log level
     * (Required)
     * 
     */
    @JsonProperty("level")
    public DtsLogMessage.Level getLevel() {
        return level;
    }

    /**
     * log level
     * (Required)
     * 
     */
    @JsonProperty("level")
    public void setLevel(DtsLogMessage.Level level) {
        this.level = level;
    }

    public DtsLogMessage withLevel(DtsLogMessage.Level level) {
        this.level = level;
        return this;
    }

    /**
     * log message
     * (Required)
     * 
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * log message
     * (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public DtsLogMessage withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * an optional stack trace if the log message corresponds to an exception
     * 
     */
    @JsonProperty("stack_trace")
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * an optional stack trace if the log message corresponds to an exception
     * 
     */
    @JsonProperty("stack_trace")
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public DtsLogMessage withStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
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

    public DtsLogMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsLogMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("level");
        sb.append('=');
        sb.append(((this.level == null)?"<null>":this.level));
        sb.append(',');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("stackTrace");
        sb.append('=');
        sb.append(((this.stackTrace == null)?"<null>":this.stackTrace));
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
        result = ((result* 31)+((this.stackTrace == null)? 0 :this.stackTrace.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.level == null)? 0 :this.level.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsLogMessage) == false) {
            return false;
        }
        DtsLogMessage rhs = ((DtsLogMessage) other);
        return (((((this.stackTrace == rhs.stackTrace)||((this.stackTrace!= null)&&this.stackTrace.equals(rhs.stackTrace)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))))&&((this.level == rhs.level)||((this.level!= null)&&this.level.equals(rhs.level))));
    }


    /**
     * log level
     * 
     */
    @Generated("jsonschema2pojo")
    public enum Level {

        FATAL("FATAL"),
        ERROR("ERROR"),
        WARN("WARN"),
        INFO("INFO"),
        DEBUG("DEBUG"),
        TRACE("TRACE");
        private final String value;
        private final static Map<String, DtsLogMessage.Level> CONSTANTS = new HashMap<String, DtsLogMessage.Level>();

        static {
            for (DtsLogMessage.Level c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        Level(String value) {
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
        public static DtsLogMessage.Level fromValue(String value) {
            DtsLogMessage.Level constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
