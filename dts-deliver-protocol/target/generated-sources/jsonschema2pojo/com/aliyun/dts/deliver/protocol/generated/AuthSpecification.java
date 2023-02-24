
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


/**
 * deprecated, switching to advanced_auth instead
 * 
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "auth_type",
    "oauth2Specification"
})
@Generated("jsonschema2pojo")
public class AuthSpecification {

    @JsonProperty("auth_type")
    private AuthSpecification.AuthType authType;
    /**
     * An object containing any metadata needed to describe this connector's Oauth flow. Deprecated, switching to advanced_auth instead
     * 
     */
    @JsonProperty("oauth2Specification")
    @JsonPropertyDescription("An object containing any metadata needed to describe this connector's Oauth flow. Deprecated, switching to advanced_auth instead")
    private OAuth2Specification oauth2Specification;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("auth_type")
    public AuthSpecification.AuthType getAuthType() {
        return authType;
    }

    @JsonProperty("auth_type")
    public void setAuthType(AuthSpecification.AuthType authType) {
        this.authType = authType;
    }

    public AuthSpecification withAuthType(AuthSpecification.AuthType authType) {
        this.authType = authType;
        return this;
    }

    /**
     * An object containing any metadata needed to describe this connector's Oauth flow. Deprecated, switching to advanced_auth instead
     * 
     */
    @JsonProperty("oauth2Specification")
    public OAuth2Specification getOauth2Specification() {
        return oauth2Specification;
    }

    /**
     * An object containing any metadata needed to describe this connector's Oauth flow. Deprecated, switching to advanced_auth instead
     * 
     */
    @JsonProperty("oauth2Specification")
    public void setOauth2Specification(OAuth2Specification oauth2Specification) {
        this.oauth2Specification = oauth2Specification;
    }

    public AuthSpecification withOauth2Specification(OAuth2Specification oauth2Specification) {
        this.oauth2Specification = oauth2Specification;
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

    public AuthSpecification withAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(AuthSpecification.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("authType");
        sb.append('=');
        sb.append(((this.authType == null)?"<null>":this.authType));
        sb.append(',');
        sb.append("oauth2Specification");
        sb.append('=');
        sb.append(((this.oauth2Specification == null)?"<null>":this.oauth2Specification));
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
        result = ((result* 31)+((this.oauth2Specification == null)? 0 :this.oauth2Specification.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.authType == null)? 0 :this.authType.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof AuthSpecification) == false) {
            return false;
        }
        AuthSpecification rhs = ((AuthSpecification) other);
        return ((((this.oauth2Specification == rhs.oauth2Specification)||((this.oauth2Specification!= null)&&this.oauth2Specification.equals(rhs.oauth2Specification)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.authType == rhs.authType)||((this.authType!= null)&&this.authType.equals(rhs.authType))));
    }

    @Generated("jsonschema2pojo")
    public enum AuthType {

        OAUTH_2_0("oauth2.0");
        private final String value;
        private final static Map<String, AuthSpecification.AuthType> CONSTANTS = new HashMap<String, AuthSpecification.AuthType>();

        static {
            for (AuthSpecification.AuthType c: values()) {
                CONSTANTS.put(c.value, c);
            }
        }

        AuthType(String value) {
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
        public static AuthSpecification.AuthType fromValue(String value) {
            AuthSpecification.AuthType constant = CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            } else {
                return constant;
            }
        }

    }

}
