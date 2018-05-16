package org.iot.platform.storage.sql.controllers.dto;

import org.iot.platform.storage.sql.domain.SmartObject;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;
import java.util.stream.Collectors;

/** Wrapper class for Smart Object to be sent on the REST body */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"id", "name", "description", "version", "multiple", "mandatory","isOma"})
public class ObjectDTO {

  private Integer id;
  private String name;
  private String description;
  private String version;
  private Boolean multiple;
  private Boolean mandatory;
  private Boolean isOma;

  public ObjectDTO() {
  }

  public ObjectDTO(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public Boolean getOma() {
    return isOma;
  }

  public void setOma(Boolean oma) {
    isOma = oma;
  }

  /**
   * Converting this object into JPA object
   *
   * @return JPA SMartObject Entity
   */
  public SmartObject convert2EntityObject() {
    return new SmartObject(
        this.id, this.name, this.description, this.version, this.multiple, this.mandatory);
  }


  @Override
  public String toString() {
    return "ObjectDTO{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", version='" + version + '\'' +
        ", multiple=" + multiple +
        ", mandatory=" + mandatory +
        ", isOma=" + isOma +
        '}';
  }
}
