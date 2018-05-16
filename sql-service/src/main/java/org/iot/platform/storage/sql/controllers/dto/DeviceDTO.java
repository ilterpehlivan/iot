package org.iot.platform.storage.sql.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceDTO {
  String id;
  String name;
  String status;
  String ownerId;
  @JsonProperty("type")
  DeviceTypeDTO deviceTypeModel;
  Date createdTime;
  Date lastUpdateTime;
  @JsonProperty("resources")
  List<DeviceResourceDTO> deviceResourceModelList;
  Map<String, String> attributes;


  public DeviceDTO() {
  }

  /**
   *
   * @param id
   * @param name
   * @param createdTime
   * @param lastUpdateTime
   * @param status
   * @param deviceTypeModel
   * @param deviceResourceModelList - can be null
   */
  public DeviceDTO(
      String id,
      String name,
      Date createdTime,
      Date lastUpdateTime,
      String status,
      DeviceTypeDTO deviceTypeModel,
      List<DeviceResourceDTO> deviceResourceModelList,Map<String, String> attributes) {
    this.id = id;
    this.name = name;
    this.createdTime = createdTime;
    this.lastUpdateTime = lastUpdateTime;
    this.status = status;
    this.deviceTypeModel = deviceTypeModel;
    if (deviceResourceModelList == null) {
      deviceResourceModelList = new ArrayList<>();
    } else {
      this.deviceResourceModelList = deviceResourceModelList;
    }

    this.attributes = attributes;

  }


  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

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

  public Date getCreatedTime() {
    return createdTime;
  }

  public void setCreatedTime(Date createdTime) {
    this.createdTime = createdTime;
  }

  public Date getLastUpdateTime() {
    return lastUpdateTime;
  }

  public void setLastUpdateTime(Date lastUpdateTime) {
    this.lastUpdateTime = lastUpdateTime;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public DeviceTypeDTO getDeviceTypeModel() {
    return deviceTypeModel;
  }

  public void setDeviceTypeModel(DeviceTypeDTO deviceTypeModel) {
    this.deviceTypeModel = deviceTypeModel;
  }

  public List<DeviceResourceDTO> getDeviceResourceModelList() {
    return deviceResourceModelList;
  }

  public void setDeviceResourceModelList(List<DeviceResourceDTO> deviceResourceModelList) {
    this.deviceResourceModelList = deviceResourceModelList;
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
