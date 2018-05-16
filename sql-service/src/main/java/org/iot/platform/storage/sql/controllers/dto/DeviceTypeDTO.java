package org.iot.platform.storage.sql.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iot.platform.storage.sql.domain.Protocol;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DeviceTypeDTO {
  String id;
  String name;
  String manufacturer;
  String model;
  List<ObjectDTO> supportedObjects;
  List<Protocol> supportedProtocols;
  Map<String, String> attributes;


  public DeviceTypeDTO() {
  }

  /**
   *
   * @param id
   * @param name
   * @param manufacturer
   * @param model
   * @param supportedObjects - can be null
   * @param attributes -- can be null
   * @param supportedProtocols -- cannot be null
   */
  public DeviceTypeDTO(
      String id,
      String name,
      String manufacturer,
      String model,
      List<ObjectDTO> supportedObjects,
      List<Protocol> supportedProtocols,
      Map<String, String> attributes) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.model = model;
    if (supportedObjects == null) {
      supportedObjects = new ArrayList<>();
    } else {
      this.supportedObjects = supportedObjects;
    }
    if (attributes == null) attributes = new HashMap<>();
    else {
      this.attributes = attributes;
    }
    this.supportedProtocols = supportedProtocols;
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

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public List<ObjectDTO> getSupportedObjects() {
    return supportedObjects;
  }

  public void setSupportedObjects(List<ObjectDTO> supportedObjects) {
    this.supportedObjects = supportedObjects;
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public List<Protocol> getSupportedProtocols() {
    return supportedProtocols;
  }

  public void setSupportedProtocols(List<Protocol> supportedProtocols) {
    this.supportedProtocols = supportedProtocols;
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
