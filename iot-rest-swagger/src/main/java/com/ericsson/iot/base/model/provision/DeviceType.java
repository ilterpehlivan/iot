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
        "nbiotWithNidd",
        "revoked",
        "sensorGroups",
        "extensionAttribute",
        "connectivity",
        "name",
        "supportedProtocols",
        "supportedSMartObjects",
        "nbiotWithIp",
        "isSystemType"
})
public class DeviceType {

    @JsonProperty("id")
    private String id;
    @JsonProperty("statusLastUpdatedTime")
    private Integer statusLastUpdatedTime;
    @JsonProperty("profileCreationTime")
    private Integer profileCreationTime;
    @JsonProperty("lastUpdatedTime")
    private Integer lastUpdatedTime;
    @JsonProperty("nbiotWithNidd")
    private Boolean nbiotWithNidd;
    @JsonProperty("revoked")
    private Boolean revoked;
    @JsonProperty("sensorGroups")
    private List<Object> sensorGroups = null;
    @JsonProperty("extensionAttribute")
    private List<Object> extensionAttribute = null;
    @JsonProperty("connectivity")
    private String connectivity;
    @JsonProperty("name")
    private String name;
    @JsonProperty("supportedProtocols")
    private List<String> supportedProtocols = null;
    @JsonProperty("supportedSMartObjects")
    private List<String> supportedSMartObjects = null;
    @JsonProperty("nbiotWithIp")
    private Boolean nbiotWithIp;
    @JsonProperty("isSystemType")
    private Boolean isSystemType;
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

    @JsonProperty("nbiotWithNidd")
    public Boolean getNbiotWithNidd() {
        return nbiotWithNidd;
    }

    @JsonProperty("nbiotWithNidd")
    public void setNbiotWithNidd(Boolean nbiotWithNidd) {
        this.nbiotWithNidd = nbiotWithNidd;
    }

    @JsonProperty("revoked")
    public Boolean getRevoked() {
        return revoked;
    }

    @JsonProperty("revoked")
    public void setRevoked(Boolean revoked) {
        this.revoked = revoked;
    }

    @JsonProperty("sensorGroups")
    public List<Object> getSensorGroups() {
        return sensorGroups;
    }

    @JsonProperty("sensorGroups")
    public void setSensorGroups(List<Object> sensorGroups) {
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

    @JsonProperty("connectivity")
    public String getConnectivity() {
        return connectivity;
    }

    @JsonProperty("connectivity")
    public void setConnectivity(String connectivity) {
        this.connectivity = connectivity;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("supportedProtocols")
    public List<String> getSupportedProtocols() {
        return supportedProtocols;
    }

    @JsonProperty("supportedProtocols")
    public void setSupportedProtocols(List<String> supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }

    @JsonProperty("supportedSMartObjects")
    public List<String> getSupportedSMartObjects() {
        return supportedSMartObjects;
    }

    @JsonProperty("supportedSMartObjects")
    public void setSupportedSMartObjects(List<String> supportedSMartObjects) {
        this.supportedSMartObjects = supportedSMartObjects;
    }

    @JsonProperty("nbiotWithIp")
    public Boolean getNbiotWithIp() {
        return nbiotWithIp;
    }

    @JsonProperty("nbiotWithIp")
    public void setNbiotWithIp(Boolean nbiotWithIp) {
        this.nbiotWithIp = nbiotWithIp;
    }

    @JsonProperty("isSystemType")
    public Boolean getIsSystemType() {
        return isSystemType;
    }

    @JsonProperty("isSystemType")
    public void setIsSystemType(Boolean isSystemType) {
        this.isSystemType = isSystemType;
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