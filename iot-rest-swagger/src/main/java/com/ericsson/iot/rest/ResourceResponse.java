package com.ericsson.iot.rest;

import com.ericsson.iot.base.model.BaseResponse;
import com.ericsson.iot.base.model.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(description = "Response for the internal resource model")
public class ResourceResponse extends BaseResponse {
    String id;
    String omaPath;
    String deviceId;
    boolean isEnabled;
    boolean isObserved;
    Date lastKnownTime;
    Data lastKnownValue;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @ApiModelProperty(value = "Internal Id of the resource.Can be used further inqueries")
    public String getId() {
        return id;

    }

    public void setId(String id) {
        this.id = id;
    }

    @ApiModelProperty(value = "OMA representation of resource f.i: 3303/0/5700")
    public String getOmaPath() {
        return omaPath;
    }

    public void setOmaPath(String omaPath) {
        this.omaPath = omaPath;
    }

    @ApiModelProperty(value = "Internal device Id.Can be used further queries")
    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }


@ApiModelProperty(value = "If resource already observed")
    public boolean isObserved() {
        return isObserved;
    }

    public void setObserved(boolean observed) {
        isObserved = observed;
    }

    public Date getLastKnownTime() {
        return lastKnownTime;
    }

    public void setLastKnownTime(Date lastKnownTime) {
        this.lastKnownTime = lastKnownTime;
    }

    @ApiModelProperty(value = "Last known value of the resource(Manual Read or Observe)")
    public Data getLastKnownValue() {
        return lastKnownValue;
    }

    public void setLastKnownValue(Data lastKnownValue) {
        this.lastKnownValue = lastKnownValue;
    }
}
