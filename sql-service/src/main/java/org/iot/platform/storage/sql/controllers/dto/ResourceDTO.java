package org.iot.platform.storage.sql.controllers.dto;

import org.iot.platform.storage.sql.domain.OmaResource;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.eclipse.leshan.core.model.ResourceModel;

/** Wrapper class for OmaResource to be sent on the REST body */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "name",
  "description",
  "multiple",
  "mandatory",
  "type",
  "units",
  "operations",
  "rangeEnumeration"
})
public class ResourceDTO {
  private Integer id;
  private String name;
  private ResourceModel.Operations operations;
  private Boolean multiple;
  private Boolean mandatory;
  private ResourceModel.Type type;
  private String rangeEnumeration;
  private String units;
  private String description;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Boolean getMultiple() {
    return multiple;
  }

  public void setMultiple(Boolean multiple) {
    this.multiple = multiple;
  }

  public Boolean getMandatory() {
    return mandatory;
  }

  public void setMandatory(Boolean mandatory) {
    this.mandatory = mandatory;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ResourceModel.Operations getOperations() {
    return operations;
  }

  public void setOperations(ResourceModel.Operations operations) {
    this.operations = operations;
  }

  public ResourceModel.Type getType() {
    return type;
  }

  public void setType(ResourceModel.Type type) {
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

  public static ResourceDTO convertResourceEntity(OmaResource omaResource) {
    ResourceDTO restModel = new ResourceDTO();
    restModel.setId(omaResource.getId());
    restModel.setDescription(omaResource.getDescription());
    restModel.setName(omaResource.getName());
    restModel.setMandatory(omaResource.isMandatory());
    restModel.setMultiple(omaResource.isMultiple());
    restModel.setOperations(omaResource.getOperations());
    restModel.setRangeEnumeration(omaResource.getRangeEnumeration());
    restModel.setType(omaResource.getType());
    restModel.setUnits(omaResource.getUnits());

    return restModel;
  }

  public OmaResource convert2EntityResource() {
    return new OmaResource(
        this.id,
        this.name,
        this.operations,
        this.multiple,
        this.mandatory,
        this.type,
        this.rangeEnumeration,
        this.units,
        this.description);
  }

  @Override
  public String toString() {
    return "ResourceDTO{"
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
}
