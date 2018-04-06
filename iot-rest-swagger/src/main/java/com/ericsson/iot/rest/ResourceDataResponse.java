package com.ericsson.iot.rest;

import com.ericsson.iot.base.model.BaseResponse;
import com.ericsson.iot.base.model.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(value ="ResourceData", description = "Data response for resource")
public class ResourceDataResponse extends BaseResponse {
    List<Data> data;

    @ApiModelProperty(value = "List of data stored under this resource",required = true)
    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }
}
