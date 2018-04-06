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
        "name",
        "isSmartObject",
        "smartObjectPath",
        "resources"
})
public class SensorGroup {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("isSmartObject")
    private Boolean isSmartObject;
    @JsonProperty("smartObjectPath")
    private String smartObjectPath;
    @JsonProperty("resources")
    private List<Resource> resources = null;
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

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("isSmartObject")
    public Boolean getIsSmartObject() {
        return isSmartObject;
    }

    @JsonProperty("isSmartObject")
    public void setIsSmartObject(Boolean isSmartObject) {
        this.isSmartObject = isSmartObject;
    }

    @JsonProperty("smartObjectPath")
    public String getSmartObjectPath() {
        return smartObjectPath;
    }

    @JsonProperty("smartObjectPath")
    public void setSmartObjectPath(String smartObjectPath) {
        this.smartObjectPath = smartObjectPath;
    }

    @JsonProperty("resources")
    public List<Resource> getResources() {
        return resources;
    }

    @JsonProperty("resources")
    public void setResources(List<Resource> resources) {
        this.resources = resources;
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