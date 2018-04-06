package com.ericsson.iot.base.model;

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
        "allowedVersionsToUpgradeFrom",
        "displayName",
        "externalURL",
        "iconURL",
        "id",
        "jobTime",
        "name",
        "notes",
        "softwareParameterContainer",
        "softwareVersionDependencies",
        "targetFileName",
        "url",
        "useSSL",
        "validFrom",
        "protocolAttrs"
})
public class SoftwareVersionList {

    @JsonProperty("allowedVersionsToUpgradeFrom")
    private List<Integer> allowedVersionsToUpgradeFrom = null;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("externalURL")
    private String externalURL;
    @JsonProperty("iconURL")
    private String iconURL;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("jobTime")
    private Integer jobTime;
    @JsonProperty("name")
    private String name;
    @JsonProperty("notes")
    private String notes;
    @JsonProperty("softwareParameterContainer")
    private SoftwareParameterContainer softwareParameterContainer;
    @JsonProperty("softwareVersionDependencies")
    private List<Integer> softwareVersionDependencies = null;
    @JsonProperty("targetFileName")
    private String targetFileName;
    @JsonProperty("url")
    private String url;
    @JsonProperty("useSSL")
    private Boolean useSSL;
    @JsonProperty("validFrom")
    private String validFrom;
    @JsonProperty("protocolAttrs")
    private ProtocolAttrs protocolAttrs;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("allowedVersionsToUpgradeFrom")
    public List<Integer> getAllowedVersionsToUpgradeFrom() {
        return allowedVersionsToUpgradeFrom;
    }

    @JsonProperty("allowedVersionsToUpgradeFrom")
    public void setAllowedVersionsToUpgradeFrom(List<Integer> allowedVersionsToUpgradeFrom) {
        this.allowedVersionsToUpgradeFrom = allowedVersionsToUpgradeFrom;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("externalURL")
    public String getExternalURL() {
        return externalURL;
    }

    @JsonProperty("externalURL")
    public void setExternalURL(String externalURL) {
        this.externalURL = externalURL;
    }

    @JsonProperty("iconURL")
    public String getIconURL() {
        return iconURL;
    }

    @JsonProperty("iconURL")
    public void setIconURL(String iconURL) {
        this.iconURL = iconURL;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("jobTime")
    public Integer getJobTime() {
        return jobTime;
    }

    @JsonProperty("jobTime")
    public void setJobTime(Integer jobTime) {
        this.jobTime = jobTime;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("notes")
    public String getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @JsonProperty("softwareParameterContainer")
    public SoftwareParameterContainer getSoftwareParameterContainer() {
        return softwareParameterContainer;
    }

    @JsonProperty("softwareParameterContainer")
    public void setSoftwareParameterContainer(SoftwareParameterContainer softwareParameterContainer) {
        this.softwareParameterContainer = softwareParameterContainer;
    }

    @JsonProperty("softwareVersionDependencies")
    public List<Integer> getSoftwareVersionDependencies() {
        return softwareVersionDependencies;
    }

    @JsonProperty("softwareVersionDependencies")
    public void setSoftwareVersionDependencies(List<Integer> softwareVersionDependencies) {
        this.softwareVersionDependencies = softwareVersionDependencies;
    }

    @JsonProperty("targetFileName")
    public String getTargetFileName() {
        return targetFileName;
    }

    @JsonProperty("targetFileName")
    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("useSSL")
    public Boolean getUseSSL() {
        return useSSL;
    }

    @JsonProperty("useSSL")
    public void setUseSSL(Boolean useSSL) {
        this.useSSL = useSSL;
    }

    @JsonProperty("validFrom")
    public String getValidFrom() {
        return validFrom;
    }

    @JsonProperty("validFrom")
    public void setValidFrom(String validFrom) {
        this.validFrom = validFrom;
    }

    @JsonProperty("protocolAttrs")
    public ProtocolAttrs getProtocolAttrs() {
        return protocolAttrs;
    }

    @JsonProperty("protocolAttrs")
    public void setProtocolAttrs(ProtocolAttrs protocolAttrs) {
        this.protocolAttrs = protocolAttrs;
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