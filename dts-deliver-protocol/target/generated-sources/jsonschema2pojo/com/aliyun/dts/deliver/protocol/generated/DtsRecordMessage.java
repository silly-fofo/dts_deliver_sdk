
package com.aliyun.dts.deliver.protocol.generated;

import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.processing.Generated;
import com.aliyun.dts.deliver.protocol.record.Record;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "namespace",
    "stream",
    "data",
    "emitted_at"
})
@Generated("jsonschema2pojo")
public class DtsRecordMessage {

    /**
     * namespace the data is associated with
     * 
     */
    @JsonProperty("namespace")
    @JsonPropertyDescription("namespace the data is associated with")
    private String namespace;
    /**
     * stream the data is associated with
     * (Required)
     * 
     */
    @JsonProperty("stream")
    @JsonPropertyDescription("stream the data is associated with")
    private String stream;
    /**
     * record data
     * (Required)
     * 
     */
    @JsonProperty("data")
    @JsonPropertyDescription("record data")
    private Record data;
    /**
     * when the data was emitted from the source. epoch in millisecond.
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    @JsonPropertyDescription("when the data was emitted from the source. epoch in millisecond.")
    private Integer emittedAt;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    /**
     * namespace the data is associated with
     * 
     */
    @JsonProperty("namespace")
    public String getNamespace() {
        return namespace;
    }

    /**
     * namespace the data is associated with
     * 
     */
    @JsonProperty("namespace")
    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public DtsRecordMessage withNamespace(String namespace) {
        this.namespace = namespace;
        return this;
    }

    /**
     * stream the data is associated with
     * (Required)
     * 
     */
    @JsonProperty("stream")
    public String getStream() {
        return stream;
    }

    /**
     * stream the data is associated with
     * (Required)
     * 
     */
    @JsonProperty("stream")
    public void setStream(String stream) {
        this.stream = stream;
    }

    public DtsRecordMessage withStream(String stream) {
        this.stream = stream;
        return this;
    }

    /**
     * record data
     * (Required)
     * 
     */
    @JsonProperty("data")
    public Record getData() {
        return data;
    }

    /**
     * record data
     * (Required)
     * 
     */
    @JsonProperty("data")
    public void setData(Record data) {
        this.data = data;
    }

    public DtsRecordMessage withData(Record data) {
        this.data = data;
        return this;
    }

    /**
     * when the data was emitted from the source. epoch in millisecond.
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    public Integer getEmittedAt() {
        return emittedAt;
    }

    /**
     * when the data was emitted from the source. epoch in millisecond.
     * (Required)
     * 
     */
    @JsonProperty("emitted_at")
    public void setEmittedAt(Integer emittedAt) {
        this.emittedAt = emittedAt;
    }

    public DtsRecordMessage withEmittedAt(Integer emittedAt) {
        this.emittedAt = emittedAt;
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

    public DtsRecordMessage withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsRecordMessage.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("namespace");
        sb.append('=');
        sb.append(((this.namespace == null)?"<null>":this.namespace));
        sb.append(',');
        sb.append("stream");
        sb.append('=');
        sb.append(((this.stream == null)?"<null>":this.stream));
        sb.append(',');
        sb.append("data");
        sb.append('=');
        sb.append(((this.data == null)?"<null>":this.data));
        sb.append(',');
        sb.append("emittedAt");
        sb.append('=');
        sb.append(((this.emittedAt == null)?"<null>":this.emittedAt));
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
        result = ((result* 31)+((this.namespace == null)? 0 :this.namespace.hashCode()));
        result = ((result* 31)+((this.emittedAt == null)? 0 :this.emittedAt.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.data == null)? 0 :this.data.hashCode()));
        result = ((result* 31)+((this.stream == null)? 0 :this.stream.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsRecordMessage) == false) {
            return false;
        }
        DtsRecordMessage rhs = ((DtsRecordMessage) other);
        return ((((((this.namespace == rhs.namespace)||((this.namespace!= null)&&this.namespace.equals(rhs.namespace)))&&((this.emittedAt == rhs.emittedAt)||((this.emittedAt!= null)&&this.emittedAt.equals(rhs.emittedAt))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.data == rhs.data)||((this.data!= null)&&this.data.equals(rhs.data))))&&((this.stream == rhs.stream)||((this.stream!= null)&&this.stream.equals(rhs.stream))));
    }

}
