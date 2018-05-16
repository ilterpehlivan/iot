package org.iot.platform.storage.sql.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "deviceId",
  "instanceId",
  "smartObject",
  "resource",
  "createDate",
  "lastUpdateTime"
})
public class DeviceResourceDTO {
  String id;

  @JsonProperty("instance")
  Integer instanceId;

  @JsonProperty("device_id")
  String deviceId;

  @JsonProperty("created_date")
  Date createDate;

  @JsonProperty("last_updated_date")
  Date lastUpdateTime;

  @JsonProperty("resource_id")
  Integer resource;

  @JsonProperty("smart_object_id")
  Integer smartObject;

  @JsonProperty("v")
  ResourceValue value;

  public DeviceResourceDTO() {}

  /**
   * @param id
   * @param instanceId
   * @param createDate
   * @param deviceId
   * @param resourceId
   * @param value
   */
  public DeviceResourceDTO(
      String id,
      Integer instanceId,
      Boolean isOma,
      Date createDate,
      Date lastUpdateDate,
      String deviceId,
      Integer resourceId,
      ResourceValue value) {
    this.id = id;
    this.instanceId = instanceId;
    this.createDate = createDate;
    this.deviceId = deviceId;
    this.value = value;
    this.resource = resourceId;
    this.lastUpdateTime = lastUpdateDate;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public Integer getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(Integer instanceId) {
    this.instanceId = instanceId;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public void setDeviceId(String deviceId) {
    this.deviceId = deviceId;
  }

  public ResourceValue getValue() {
    return value;
  }

  public void setValue(ResourceValue value) {
    this.value = value;
  }

  public Integer getResource() {
    return resource;
  }

  public void setResource(Integer resource) {
    this.resource = resource;
  }

  public Integer getSmartObject() {
    return smartObject;
  }

  public void setSmartObject(Integer smartObject) {
    this.smartObject = smartObject;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  @Override
  public String toString() {
    ObjectMapper mapper = new ObjectMapper();

    String jsonString = "";
    try {
      mapper.enable(SerializationFeature.INDENT_OUTPUT);
      jsonString = mapper.writeValueAsString(this);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }

    return jsonString;
  }
}
