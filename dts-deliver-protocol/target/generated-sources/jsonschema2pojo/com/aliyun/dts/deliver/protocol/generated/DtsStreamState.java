
package com.aliyun.dts.deliver.protocol.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.JsonNode;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "stream_descriptor",
    "stream_state"
})
@Generated("jsonschema2pojo")
public class DtsStreamState {

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_descriptor")
    private StreamDescriptor streamDescriptor;
    /**
     * the state data
     * 
     */
    @JsonProperty("stream_state")
    @JsonPropertyDescription("the state data")
    private JsonNode streamState;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_descriptor")
    public StreamDescriptor getStreamDescriptor() {
        return streamDescriptor;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_descriptor")
    public void setStreamDescriptor(StreamDescriptor streamDescriptor) {
        this.streamDescriptor = streamDescriptor;
    }

    public DtsStreamState withStreamDescriptor(StreamDescriptor streamDescriptor) {
        this.streamDescriptor = streamDescriptor;
        return this;
    }

    /**
     * the state data
     * 
     */
    @JsonProperty("stream_state")
    public JsonNode getStreamState() {
        return streamState;
    }

    /**
     * the state data
     * 
     */
    @JsonProperty("stream_state")
    public void setStreamState(JsonNode streamState) {
        this.streamState = streamState;
    }

    public DtsStreamState withStreamState(JsonNode streamState) {
        this.streamState = streamState;
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

    public DtsStreamState withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsStreamState.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("streamDescriptor");
        sb.append('=');
        sb.append(((this.streamDescriptor == null)?"<null>":this.streamDescriptor));
        sb.append(',');
        sb.append("streamState");
        sb.append('=');
        sb.append(((this.streamState == null)?"<null>":this.streamState));
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
        result = ((result* 31)+((this.streamDescriptor == null)? 0 :this.streamDescriptor.hashCode()));
        result = ((result* 31)+((this.streamState == null)? 0 :this.streamState.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsStreamState) == false) {
            return false;
        }
        DtsStreamState rhs = ((DtsStreamState) other);
        return ((((this.streamDescriptor == rhs.streamDescriptor)||((this.streamDescriptor!= null)&&this.streamDescriptor.equals(rhs.streamDescriptor)))&&((this.streamState == rhs.streamState)||((this.streamState!= null)&&this.streamState.equals(rhs.streamState))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
