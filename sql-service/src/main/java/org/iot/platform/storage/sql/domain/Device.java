package org.iot.platform.storage.sql.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Device {
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

  @OneToOne(targetEntity = DeviceType.class)
  @JoinColumn(name = "device_type", nullable = false)
  DeviceType type;

  String ownerId;
  LocalDateTime lastUpdatedTime;
  LocalDateTime profileCreationTime;

  @Enumerated(EnumType.STRING)
  Status status;

  @OneToMany(
    fetch = FetchType.LAZY,
    targetEntity = DeviceResource.class,
    cascade = CascadeType.ALL,
    mappedBy = "device",
    orphanRemoval = true
  )
  @ElementCollection
  List<DeviceResource> resources;

  @ElementCollection
  @Column(length = 1000)
  Map<String, String> attributes;

  // TODO: add software dependencies

  public enum Status {
    CREATED,
    BOOTSTRAPPED,
    CONNECTED,
    OFFLINE
  }

  public DeviceType getType() {
    return type;
  }

  public void setType(DeviceType type) {
    this.type = type;
  }

  public LocalDateTime getLastUpdatedTime() {
    return lastUpdatedTime;
  }

  public void setLastUpdatedTime(LocalDateTime lastUpdatedTime) {
    this.lastUpdatedTime = lastUpdatedTime;
  }

  public LocalDateTime getProfileCreationTime() {
    return profileCreationTime;
  }

  public void setProfileCreationTime(LocalDateTime profileCreationTime) {
    this.profileCreationTime = profileCreationTime;
  }

  public List<DeviceResource> getResources() {
    return resources;
  }

  public void setResources(List<DeviceResource> resources) {
    this.resources = resources;
  }

  public void addResource(DeviceResource resource){
    if(this.resources == null) this.resources = new ArrayList<>();
    this.resources.add(resource);
  }

  public Map<String, String> getAttributes() {
    return attributes;
  }

  public void setAttributes(Map<String, String> attributes) {
    this.attributes = attributes;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Status getStatus() {
    return status;
  }

  public void setStatus(Status status) {
    this.status = status;
  }

  public String getOwnerId() {
    return ownerId;
  }

  public void setOwnerId(String ownerId) {
    this.ownerId = ownerId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Device device = (Device) o;
    return Objects.equals(id, device.id);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id);
  }

  @Override
  public String toString() {
    return "Device{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", type=" + type +
        ", ownerId='" + ownerId + '\'' +
        ", lastUpdatedTime=" + lastUpdatedTime +
        ", profileCreationTime=" + profileCreationTime +
        ", status=" + status +
        ", resources=" + resources +
        ", attributes=" + attributes +
        '}';
  }
}
