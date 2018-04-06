package com.ericsson.iot.rest;


import com.ericsson.iot.base.model.BaseResponse;
import com.ericsson.iot.base.model.Sensor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;

import java.util.Date;
import java.util.List;

@ApiModel(value ="DeviceData", description = "Data response for device level")
public class DeviceDataResponse extends BaseResponse {
    List<Sensor> sensors;

    @ApiModelProperty(value = "List of sensors under the device", required = true)
    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}


