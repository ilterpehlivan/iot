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
        "name",
        "protocol",
        "softwareRule",
        "isFirmware",
        "versions"
})
public class Software {

    @JsonProperty("name")
    private String name;
    @JsonProperty("protocol")
    private String protocol;
    @JsonProperty("softwareRule")
    private SoftwareRule softwareRule;
    @JsonProperty("isFirmware")
    private boolean isFirmware;
    @JsonProperty("versions")
    private SoftwareVersionList softwareVersions;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("protocol")
    public String getProtocol() {
        return protocol;
    }

    @JsonProperty("protocol")
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    @JsonProperty("softwareRule")
    public SoftwareRule getSoftwareRule() {
        return softwareRule;
    }

    @JsonProperty("softwareRule")
    public void setSoftwareRule(SoftwareRule softwareRule) {
        this.softwareRule = softwareRule;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }


    @JsonAnyGetter
    public boolean isFirmware() {
        return isFirmware;
    }

    @JsonAnySetter
    public void setFirmware(boolean firmware) {
        isFirmware = firmware;
    }

    @JsonAnyGetter
    public SoftwareVersionList getSoftwareVersions() {
        return softwareVersions;
    }

    @JsonAnySetter
    public void setSoftwareVersions(SoftwareVersionList softwareVersions) {
        this.softwareVersions = softwareVersions;
    }
}