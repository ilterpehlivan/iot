package com.ericsson.iot.base.model.provision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "id",
        "statusLastUpdatedTime",
        "profileCreationTime",
        "lastUpdatedTime",
        "deviceTypeId",
        "sensorGroups",
        "extensionAttribute",
        "supportedProtocols",
        "status",
        "softwares"
})
public class Device {

    @JsonProperty("id")
    private String id;
    @JsonProperty("statusLastUpdatedTime")
    private Integer statusLastUpdatedTime;
    @JsonProperty("profileCreationTime")
    private Integer profileCreationTime;
    @JsonProperty("lastUpdatedTime")
    private Integer lastUpdatedTime;
    @JsonProperty("deviceTypeId")
    private String deviceTypeId;
    @JsonProperty("sensorGroups")
    private List<SensorGroup> sensorGroups = null;
    @JsonProperty("extensionAttribute")
    private List<Object> extensionAttribute = null;
    @JsonProperty("supportedProtocols")
    private List<String> supportedProtocols = null;
    @JsonProperty("status")
    private String status;
    @JsonProperty("softwares")
    private List<String> softwares;

    public List<String> getSoftwares() {
        return softwares;
    }

    public void setSoftwares(List<String> softwares) {
        this.softwares = softwares;
    }

    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }

    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("statusLastUpdatedTime")
    public Integer getStatusLastUpdatedTime() {
        return statusLastUpdatedTime;
    }

    @JsonProperty("statusLastUpdatedTime")
    public void setStatusLastUpdatedTime(Integer statusLastUpdatedTime) {
        this.statusLastUpdatedTime = statusLastUpdatedTime;
    }

    @JsonProperty("profileCreationTime")
    public Integer getProfileCreationTime() {
        return profileCreationTime;
    }

    @JsonProperty("profileCreationTime")
    public void setProfileCreationTime(Integer profileCreationTime) {
        this.profileCreationTime = profileCreationTime;
    }

    @JsonProperty("lastUpdatedTime")
    public Integer getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    @JsonProperty("lastUpdatedTime")
    public void setLastUpdatedTime(Integer lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    @JsonProperty("deviceTypeId")
    public String getDeviceTypeId() {
        return deviceTypeId;
    }

    @JsonProperty("deviceTypeId")
    public void setDeviceTypeId(String deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    @JsonProperty("sensorGroups")
    public List<SensorGroup> getSensorGroups() {
        return sensorGroups;
    }

    @JsonProperty("sensorGroups")
    public void setSensorGroups(List<SensorGroup> sensorGroups) {
        this.sensorGroups = sensorGroups;
    }

    @JsonProperty("extensionAttribute")
    public List<Object> getExtensionAttribute() {
        return extensionAttribute;
    }

    @JsonProperty("extensionAttribute")
    public void setExtensionAttribute(List<Object> extensionAttribute) {
        this.extensionAttribute = extensionAttribute;
    }

    @JsonProperty("supportedProtocols")
    public List<String> getSupportedProtocols() {
        return supportedProtocols;
    }

    @JsonProperty("supportedProtocols")
    public void setSupportedProtocols(List<String> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
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