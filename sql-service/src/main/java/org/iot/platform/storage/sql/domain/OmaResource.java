package org.iot.platform.storage.sql.domain;

import java.util.Objects;
import javax.persistence.*;

import org.eclipse.leshan.core.model.ResourceModel.Operations;
import org.eclipse.leshan.core.model.ResourceModel.Type;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"resourceId", "object_id"})})
public class OmaResource {

  @Id @GeneratedValue private Integer id;

  private int resourceId;

  @Column(length = 1060)
  private String name;

  @Enumerated(EnumType.STRING)
  private Operations operations;

  private boolean multiple;
  private boolean mandatory;

  @Enumerated(EnumType.STRING)
  private Type type;

  private String rangeEnumeration;
  private String units;

  @Column(length = 2048)
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OBJECT_ID")
  private SmartObject smartobject;

  @Enumerated(EnumType.STRING)
  ResourceCategory category;

  public SmartObject getSmartobject() {
    return smartobject;
  }

  public void setSmartobject(SmartObject smartobject) {
    this.smartobject = smartobject;
  }

  public OmaResource() {}

  /**
   * @param id - OMA resource ID
   * @param name - OMA resource name
   * @param operations
   * @param multiple
   * @param mandatory
   * @param type
   * @param rangeEnumeration
   * @param units
   * @param description
   */
  public OmaResource(
      int id,
      String name,
      Operations operations,
      boolean multiple,
      boolean mandatory,
      Type type,
      String rangeEnumeration,
      String units,
      String description) {
    this.resourceId = id;
    this.name = name;
    this.operations = operations;
    this.multiple = multiple;
    this.mandatory = mandatory;
    this.type = type;
    this.rangeEnumeration = rangeEnumeration;
    this.units = units;
    this.description = description;
    this.category = ResourceCategory.getCategory(id);
  }


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public ResourceCategory getCategory() {
    return category;
  }

  public void setCategory(ResourceCategory category) {
    this.category = category;
  }

  public int getResourceId() {
    return resourceId;
  }

  public void setResourceId(int resourceId) {
    this.resourceId = resourceId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Operations getOperations() {
    return operations;
  }

  public void setOperations(Operations operations) {
    this.operations = operations;
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

  public Type getType() {
    return type;
  }

  public void setType(Type type) {
    this.type = type;
  }

  public String getRangeEnumeration() {
    return rangeEnumeration;
  }

  public void setRangeEnumeration(String rangeEnumeration) {
    this.rangeEnumeration = rangeEnumeration;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  //    public enum Operations {
  //        NONE, R, W, RW, E
  //    }
  //
  //    public enum Type {
  //        STRING, INTEGER, FLOAT, BOOLEAN, OPAQUE, TIME, OBJLNK
  //    }

  @Override
  public String toString() {
    return "OmaResource{"
        + "id="
        + id
        + ", name='"
        + name
        + '\''
        + ", operations="
        + operations
        + ", multiple="
        + multiple
        + ", mandatory="
        + mandatory
        + ", type="
        + type
        + ", rangeEnumeration='"
        + rangeEnumeration
        + '\''
        + ", units='"
        + units
        + '\''
        + ", description='"
        + description
        + '\''
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OmaResource that = (OmaResource) o;
    return resourceId == that.resourceId
        && multiple == that.multiple
        && mandatory == that.mandatory
        && Objects.equals(name, that.name)
        && operations == that.operations
        && type == that.type
        && Objects.equals(rangeEnumeration, that.rangeEnumeration)
        && Objects.equals(units, that.units)
        && Objects.equals(description, that.description)
        && category == that.category;
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        resourceId,
        name,
        operations,
        multiple,
        mandatory,
        type,
        rangeEnumeration,
        units,
        description,
        category);
  }
}
