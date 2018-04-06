package com.ericsson.iot.base.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import io.swagger.annotations.ApiModel;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "boot",
        "bootStrap",
        "nextContact",
        "manualBatchFlows"
})
@ApiModel(value = "WhenApply")
public class SoftwareRule {

    @JsonProperty("boot")
    private Boolean boot;
    @JsonProperty("bootStrap")
    private Boolean bootStrap;
    @JsonProperty("nextContact")
    private Boolean nextContact;
    @JsonProperty("manualBatchFlows")
    private Boolean manualBatchFlows;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("boot")
    public Boolean getBoot() {
        return boot;
    }

    @JsonProperty("boot")
    public void setBoot(Boolean boot) {
        this.boot = boot;
    }

    @JsonProperty("bootStrap")
    public Boolean getBootStrap() {
        return bootStrap;
    }

    @JsonProperty("bootStrap")
    public void setBootStrap(Boolean bootStrap) {
        this.bootStrap = bootStrap;
    }

    @JsonProperty("nextContact")
    public Boolean getNextContact() {
        return nextContact;
    }

    @JsonProperty("nextContact")
    public void setNextContact(Boolean nextContact) {
        this.nextContact = nextContact;
    }

    @JsonProperty("manualBatchFlows")
    public Boolean getManualBatchFlows() {
        return manualBatchFlows;
    }

    @JsonProperty("manualBatchFlows")
    public void setManualBatchFlows(Boolean manualBatchFlows) {
        this.manualBatchFlows = manualBatchFlows;
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