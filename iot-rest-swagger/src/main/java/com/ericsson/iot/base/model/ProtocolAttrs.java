package com.ericsson.iot.base.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "retrieveAllParameterValues",
        "fumo"
})
public class ProtocolAttrs {

    @JsonProperty("retrieveAllParameterValues")
    private Boolean retrieveAllParameterValues;
    @JsonProperty("fumo")
    private String fumo;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("retrieveAllParameterValues")
    public Boolean getRetrieveAllParameterValues() {
        return retrieveAllParameterValues;
    }

    @JsonProperty("retrieveAllParameterValues")
    public void setRetrieveAllParameterValues(Boolean retrieveAllParameterValues) {
        this.retrieveAllParameterValues = retrieveAllParameterValues;
    }

    @JsonProperty("fumo")
    public String getFumo() {
        return fumo;
    }

    @JsonProperty("fumo")
    public void setFumo(String fumo) {
        this.fumo = fumo;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}