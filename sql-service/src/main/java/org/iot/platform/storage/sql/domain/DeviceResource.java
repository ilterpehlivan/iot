package org.iot.platform.storage.sql.domain;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"instanceId", "resource_id"})})
public class DeviceResource {
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

  int instanceId;

  LocalDateTime lastUpdatedTime;
  LocalDateTime creationTime;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "device_id", nullable = false)
  Device device;

  @OneToOne(targetEntity = SmartObject.class)
  @JoinColumn(name = "object_id", nullable = false)
  SmartObject smartobject;

  @OneToOne(targetEntity = OmaResource.class)
  @JoinColumn(name = "resource_id", nullable = false)
  OmaResource resource;

  // Values
  //  boolean boolValue;
  //  int intValue;
  //
  //  @Column(length = 1024)
  //  String strValue;

  @Lob private byte[] bytValue;

  // 3303/0 , 3303/1

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public int getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(int instanceId) {
    this.instanceId = instanceId;
  }

  public LocalDateTime getCreationTime() {
    return creationTime;
  }

  public void setCreationTime(LocalDateTime creationTime) {
    this.creationTime = creationTime;
  }

  public Device getDevice() {
    return device;
  }

  public void setDevice(Device device) {
    this.device = device;
  }

  public OmaResource getResource() {
    return resource;
  }

  public void setResource(OmaResource resource) {
    this.resource = resource;
  }

  //  public boolean isBoolValue() {
  //    return boolValue;
  //  }
  //
  //  public void setBoolValue(boolean boolValue) {
  //    this.boolValue = boolValue;
  //  }
  //
  //  public int getIntValue() {
  //    return intValue;
  //  }
  //
  //  public void setIntValue(int intValue) {
  //    this.intValue = intValue;
  //  }
  //
  //  public String getStrValue() {
  //    return strValue;
  //  }
  //
  //  public void setStrValue(String strValue) {
  //    this.strValue = strValue;
  //  }

  public byte[] getBytValue() {
    return bytValue;
  }

  public void setBytValue(byte[] bytValue) {
    this.bytValue = bytValue;
  }

  public SmartObject getSmartobject() {
    return smartobject;
  }

  public void setSmartobject(SmartObject smartobject) {
    this.smartobject = smartobject;
  }


  public LocalDateTime getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DeviceResource resource1 = (DeviceResource) o;
    return instanceId == resource1.instanceId
        && Objects.equals(id, resource1.id)
        && Objects.equals(device, resource1.device)
        && Objects.equals(smartobject, resource1.smartobject)
        && Objects.equals(resource, resource1.resource);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, instanceId, device, smartobject, resource);
  }

  @Override
  public String toString() {
    return "DeviceResource{"
        + "id="
        + id
        + ", instanceId="
        + instanceId
        + ", creationTime="
        + creationTime
        + ", smartobject="
        + smartobject
        + ", resource="
        + resource
        + ", bytValue="
        + Arrays.toString(bytValue)
        + '}';
  }
}
