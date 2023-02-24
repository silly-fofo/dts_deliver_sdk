
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
    "message",
    "internal_message",
    "stack_trace",
    "failure_type"
})
@Generated("jsonschema2pojo")
public class DtsErrorTraceMessage {

    /**
     * A user-friendly message that indicates the cause of the error
     * (Required)
     * 
     */
    @JsonProperty("message")
    @JsonPropertyDescription("A user-friendly message that indicates the cause of the error")
    private String message;
    /**
     * The internal error that caused the failure
     * 
     */
    @JsonProperty("internal_message")
    @JsonPropertyDescription("The internal error that caused the failure")
    private String internalMessage;
    /**
     * The full stack trace of the error
     * 
     */
    @JsonProperty("stack_trace")
    @JsonPropertyDescription("The full stack trace of the error")
    private String stackTrace;
    /**
     * The type of error
     * 
     */
    @JsonProperty("failure_type")
    @JsonPropertyDescription("The type of error")
    private DtsErrorTraceMessage.FailureType failureType;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * A user-friendly message that indicates the cause of the error
     * (Required)
     * 
     */
    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    /**
     * A user-friendly message that indicates the cause of the error
     * (Required)
     * 
     */
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }

    public DtsErrorTraceMessage withMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * The internal error that caused the failure
     * 
     */
    @JsonProperty("internal_message")
    public String getInternalMessage() {
        return internalMessage;
    }

    /**
     * The internal error that caused the failure
     * 
     */
    @JsonProperty("internal_message")
    public void setInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
    }

    public DtsErrorTraceMessage withInternalMessage(String internalMessage) {
        this.internalMessage = internalMessage;
        return this;
    }

    /**
     * The full stack trace of the error
     * 
     */
    @JsonProperty("stack_trace")
    public String getStackTrace() {
        return stackTrace;
    }

    /**
     * The full stack trace of the error
     * 
     */
    @JsonProperty("stack_trace")
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public DtsErrorTraceMessage withStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
        return this;
    }

    /**
     * The type of error
     * 
     */
    @JsonProperty("failure_type")
    public DtsErrorTraceMessage.FailureType getFailureType() {
        return failureType;
    }

    /**
     * The type of error
     * 
     */
    @JsonProperty("failure_type")
    public void setFailureType(DtsErrorTraceMessage.FailureType failureType) {
        this.failureType = failureType;
    }

    public DtsErrorTraceMessage withFailureType(DtsErrorTraceMessage.FailureType failureType) {
        this.failureType = failureType;
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

    public DtsErrorTraceMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsErrorTraceMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("message");
        sb.append('=');
        sb.append(((this.message == null)?"<null>":this.message));
        sb.append(',');
        sb.append("internalMessage");
        sb.append('=');
        sb.append(((this.internalMessage == null)?"<null>":this.internalMessage));
        sb.append(',');
        sb.append("stackTrace");
        sb.append('=');
        sb.append(((this.stackTrace == null)?"<null>":this.stackTrace));
        sb.append(',');
        sb.append("failureType");
        sb.append('=');
        sb.append(((this.failureType == null)?"<null>":this.failureType));
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
        result = ((result* 31)+((this.failureType == null)? 0 :this.failureType.hashCode()));
        result = ((result* 31)+((this.stackTrace == null)? 0 :this.stackTrace.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.message == null)? 0 :this.message.hashCode()));
        result = ((result* 31)+((this.internalMessage == null)? 0 :this.internalMessage.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsErrorTraceMessage) == false) {
            return false;
        }
        DtsErrorTraceMessage rhs = ((DtsErrorTraceMessage) other);
        return ((((((this.failureType == rhs.failureType)||((this.failureType!= null)&&this.failureType.equals(rhs.failureType)))&&((this.stackTrace == rhs.stackTrace)||((this.stackTrace!= null)&&this.stackTrace.equals(rhs.stackTrace))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.message == rhs.message)||((this.message!= null)&&this.message.equals(rhs.message))))&&((this.internalMessage == rhs.internalMessage)||((this.internalMessage!= null)&&this.internalMessage.equals(rhs.internalMessage))));
    }


    /**
     * The type of error
     * 
     */
    @Generated("jsonschema2pojo")
    public enum FailureType {

        SYSTEM_ERROR("system_error"),
        CONFIG_ERROR("config_error");
        private final String value;
        private final static Map<String, DtsErrorTraceMessage.FailureType> CONSTANTS = new HashMap<String, DtsErrorTraceMessage.FailureType>();

        static {
            for (DtsErrorTraceMessage.FailureType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        FailureType(String value) {
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
        public static DtsErrorTraceMessage.FailureType fromValue(String value) {
            DtsErrorTraceMessage.FailureType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
