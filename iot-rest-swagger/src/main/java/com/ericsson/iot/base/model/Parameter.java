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
        "defaultValue",
        "displayName",
        "id",
        "parentId",
        "name",
        "description",
        "dataType",
        "accessTypes",
        "subParameter"
})
public class Parameter {

    @JsonProperty("defaultValue")
    private String defaultValue;
    @JsonProperty("displayName")
    private String displayName;
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("parentId")
    private Integer parentId;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("dataType")
    private String dataType;
    @JsonProperty("accessTypes")
    private List<String> accessTypes = null;
    @JsonProperty("subParameter")
    private List<SubParameter> subParameter = null;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("defaultValue")
    public String getDefaultValue() {
        return defaultValue;
    }

    @JsonProperty("defaultValue")
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    @JsonProperty("displayName")
    public String getDisplayName() {
        return displayName;
    }

    @JsonProperty("displayName")
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("parentId")
    public Integer getParentId() {
        return parentId;
    }

    @JsonProperty("parentId")
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("dataType")
    public String getDataType() {
        return dataType;
    }

    @JsonProperty("dataType")
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    @JsonProperty("accessTypes")
    public List<String> getAccessTypes() {
        return accessTypes;
    }

    @JsonProperty("accessTypes")
    public void setAccessTypes(List<String> accessTypes) {
        this.accessTypes = accessTypes;
    }

    @JsonProperty("subParameter")
    public List<SubParameter> getSubParameter() {
        return subParameter;
    }

    @JsonProperty("subParameter")
    public void setSubParameter(List<SubParameter> subParameter) {
        this.subParameter = subParameter;
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