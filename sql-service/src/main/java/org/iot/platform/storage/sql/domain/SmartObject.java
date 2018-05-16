package org.iot.platform.storage.sql.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
public class SmartObject {

  @Id private int id;

  @Column(length = 1024)
  private String name;

  @Column(length = 1024)
  private String description;

  private String version;
  private boolean multiple;
  private boolean mandatory;
  private boolean isOma;

  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
  @JoinColumn(name = "OBJECT_ID")
  private DeviceType devicetype;

  public SmartObject() {}

  public SmartObject(
      int id,
      String name,
      String description,
      String version,
      boolean multiple,
      boolean mandatory) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.version = version;
    this.multiple = multiple;
    this.mandatory = mandatory;
    this.isOma = id >= 0 && id < 1024;
  }

  public DeviceType getDevicetype() {
    return devicetype;
  }

  public void setDevicetype(DeviceType devicetype) {
    this.devicetype = devicetype;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public boolean isMultiple() {
    return multiple;
  }

  public void setMultiple(boolean multiple) {
    this.multiple = multiple;
  }

  public boolean isMandatory() {
    return mandatory;
  }

  public void setMandatory(boolean mandatory) {
    this.mandatory = mandatory;
  }

  //  public List<OmaResource> getOmaResources() {
  //    return omaResources;
  //  }
  //
  //  public void setOmaResources(List<OmaResource> omaResources) {
  //    this.omaResources = omaResources;
  //  }

  public boolean isOma() {
    return isOma;
  }

  public void setOma(boolean oma) {
    isOma = oma;
  }

  @Override
  public String toString() {
    return "SmartObject{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", description='"
        + description
        + '\''
        + ", version='"
        + version
        + '\''
        + ", multiple="
        + multiple
        + ", mandatory="
        + mandatory
        + '}';
  }

  //  public boolean isResourceIdBelongs2Object(Integer id) {
  //    if (omaResources == null || omaResources.isEmpty()) return false;
  //    return omaResources.stream().filter(r -> r.getResourceId() == id).findAny().isPresent();
  //  }
}
