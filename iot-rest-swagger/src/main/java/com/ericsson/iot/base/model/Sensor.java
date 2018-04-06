package com.ericsson.iot.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@ApiModel(description = "Representing physical sensor as stored data")
public class Sensor implements Serializable {
	String id;
	String name;
	Map<String, String> meta;
	List<Resource> resources;

	@ApiModelProperty(value = "Id of the sensor" , required=true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ApiModelProperty(value = "Additional attributes stored for sensor")
    public Map<String, String> getMeta() {
        return meta;
    }

    public void setMeta(Map<String, String> meta) {
        this.meta = meta;
    }

    @ApiModelProperty(value = "Representing actual resources of sensor",required = true)
    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
