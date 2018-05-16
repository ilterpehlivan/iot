package org.iot.platform.storage.sql.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

@Entity
@DynamicUpdate
public class DeviceType {
  @Id
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(
    name = "UUID",
    strategy = "org.hibernate.id.UUIDGenerator",
    parameters = {
      @org.hibernate.annotations.Parameter(
        name = "uuid_gen_strategy_class",
        value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
      )
    }
  )
  @Column(name = "id", updatable = false, nullable = false)
  UUID id;

  String name;
  String model;
  String manufacturer;

  @ElementCollection
  @Column(length = 256, nullable = false)
  List<Protocol> supportedprotocols;
  @ElementCollection
  @Column(length = 1000)
  Map<String, String> attributes;

  @OneToMany(
    targetEntity = SmartObject.class,
    mappedBy = "devicetype",
    cascade = CascadeType.MERGE,
    fetch = FetchType.LAZY
  )
  //    @JoinColumn(name = "object_id", nullable = false)
  List<SmartObject> smartobjects;

  public List<SmartObject> getSmartobjects() {
    if(smartobjects == null) return smartobjects = new ArrayList<>();
    return smartobjects;
  }

  public void setSmartobjects(List<SmartObject> smartobjects) {
    this.smartobjects = smartobjects;
  }

  public UUID getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public void setManufacturer(String manufacturer) {
    this.manufacturer = manufacturer;
  }

  public List<Protocol> getSupportedprotocols() {
    return supportedprotocols;
  }

  public void setSupportedprotocols(List<Protocol> supportedprotocols) {
    this.supportedprotocols = supportedprotocols;
  }

  public Map<String, String> getAttributes() {
    if(attributes == null) return attributes = new HashMap<>();
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public void addProtocol(Protocol protocol) {
    if(this.supportedprotocols == null) this.supportedprotocols = new ArrayList<>();
    this.supportedprotocols.add(protocol);
  }

  public void setId(UUID id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "DeviceType{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", model='" + model + '\'' +
        ", manufacturer='" + manufacturer + '\'' +
        ", supportedprotocols=" + supportedprotocols +
        ", attributes=" + attributes +
        ", smartobjects=" + smartobjects +
        '}';
  }
}
