
package com.aliyun.dts.deliver.protocol.generated;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
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
    "shared_state",
    "stream_states"
})
@Generated("jsonschema2pojo")
public class DtsGlobalState {

    /**
     * the state data
     * 
     */
    @JsonProperty("shared_state")
    @JsonPropertyDescription("the state data")
    private JsonNode sharedState;
    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_states")
    private List<DtsStreamState> streamStates = new ArrayList<DtsStreamState>();
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * the state data
     * 
     */
    @JsonProperty("shared_state")
    public JsonNode getSharedState() {
        return sharedState;
    }

    /**
     * the state data
     * 
     */
    @JsonProperty("shared_state")
    public void setSharedState(JsonNode sharedState) {
        this.sharedState = sharedState;
    }

    public DtsGlobalState withSharedState(JsonNode sharedState) {
        this.sharedState = sharedState;
        return this;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_states")
    public List<DtsStreamState> getStreamStates() {
        return streamStates;
    }

    /**
     * 
     * (Required)
     * 
     */
    @JsonProperty("stream_states")
    public void setStreamStates(List<DtsStreamState> streamStates) {
        this.streamStates = streamStates;
    }

    public DtsGlobalState withStreamStates(List<DtsStreamState> streamStates) {
        this.streamStates = streamStates;
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

    public DtsGlobalState withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsGlobalState.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("sharedState");
        sb.append('=');
        sb.append(((this.sharedState == null)?"<null>":this.sharedState));
        sb.append(',');
        sb.append("streamStates");
        sb.append('=');
        sb.append(((this.streamStates == null)?"<null>":this.streamStates));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sharedState == null)? 0 :this.sharedState.hashCode()));
        result = ((result* 31)+((this.streamStates == null)? 0 :this.streamStates.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsGlobalState) == false) {
            return false;
        }
        DtsGlobalState rhs = ((DtsGlobalState) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.sharedState == rhs.sharedState)||((this.sharedState!= null)&&this.sharedState.equals(rhs.sharedState))))&&((this.streamStates == rhs.streamStates)||((this.streamStates!= null)&&this.streamStates.equals(rhs.streamStates))));
    }

}
