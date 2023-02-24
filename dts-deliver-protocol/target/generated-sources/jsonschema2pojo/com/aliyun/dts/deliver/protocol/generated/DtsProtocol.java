
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


/**
 * DtsProtocol
 * <p>
 * DtsProtocol structs
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "dts_message",
    "configured_dts_catalog"
})
@Generated("jsonschema2pojo")
public class DtsProtocol {

    @JsonProperty("dts_message")
    private DtsMessage dtsMessage;
    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("configured_dts_catalog")
    @JsonPropertyDescription("Dts stream schema catalog")
    private ConfiguredDtsCatalog configuredDtsCatalog;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("dts_message")
    public DtsMessage getDtsMessage() {
        return dtsMessage;
    }

    @JsonProperty("dts_message")
    public void setDtsMessage(DtsMessage dtsMessage) {
        this.dtsMessage = dtsMessage;
    }

    public DtsProtocol withDtsMessage(DtsMessage dtsMessage) {
        this.dtsMessage = dtsMessage;
        return this;
    }

    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("configured_dts_catalog")
    public ConfiguredDtsCatalog getConfiguredDtsCatalog() {
        return configuredDtsCatalog;
    }

    /**
     * Dts stream schema catalog
     * 
     */
    @JsonProperty("configured_dts_catalog")
    public void setConfiguredDtsCatalog(ConfiguredDtsCatalog configuredDtsCatalog) {
        this.configuredDtsCatalog = configuredDtsCatalog;
    }

    public DtsProtocol withConfiguredDtsCatalog(ConfiguredDtsCatalog configuredDtsCatalog) {
        this.configuredDtsCatalog = configuredDtsCatalog;
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

    public DtsProtocol withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(DtsProtocol.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("dtsMessage");
        sb.append('=');
        sb.append(((this.dtsMessage == null)?"<null>":this.dtsMessage));
        sb.append(',');
        sb.append("configuredDtsCatalog");
        sb.append('=');
        sb.append(((this.configuredDtsCatalog == null)?"<null>":this.configuredDtsCatalog));
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
        result = ((result* 31)+((this.dtsMessage == null)? 0 :this.dtsMessage.hashCode()));
        result = ((result* 31)+((this.configuredDtsCatalog == null)? 0 :this.configuredDtsCatalog.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof DtsProtocol) == false) {
            return false;
        }
        DtsProtocol rhs = ((DtsProtocol) other);
        return ((((this.dtsMessage == rhs.dtsMessage)||((this.dtsMessage!= null)&&this.dtsMessage.equals(rhs.dtsMessage)))&&((this.configuredDtsCatalog == rhs.configuredDtsCatalog)||((this.configuredDtsCatalog!= null)&&this.configuredDtsCatalog.equals(rhs.configuredDtsCatalog))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))));
    }

}
