package com.ericsson.iot.rest;

import com.ericsson.iot.base.model.BaseResponse;
import com.ericsson.iot.base.model.Resource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value ="SensorData", description = "Data response for sensor level")
public class SensorDataResponse extends BaseResponse {
    List<Resource> resources;

    @ApiModelProperty(value = "List of resources", required = true)
    public List<Resource> getResources() {
        return resources;
    }

    public void setResources(List<Resource> resources) {
        this.resources = resources;
    }
}
