package org.iot.platform.storage.sql.converters;

import java.util.List;
import org.iot.platform.storage.sql.controllers.dto.DeviceResourceDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
  imports = ResourceValue.class,
  componentModel = "spring",
  uses = {BaseMapper.class}
)
public interface DeviceResourceMapper {

  DeviceResourceMapper MAPPER = Mappers.getMapper(DeviceResourceMapper.class);
  String LOCAL_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";

  @Mappings({
    @Mapping(target = "id", expression = "java(  resource.getId().toString() )"),
    @Mapping(target = "resource", source = "resource.resource.resourceId"),
    @Mapping(target = "smartObject", source = "resource.smartobject.id"),
    @Mapping(
      target = "value",
      expression =
          "java( new ResourceValue().getValue(resource.getBytValue(), resource.getResource().getType()) )"
    ),
    @Mapping(
      target = "createDate",
      dateFormat = LOCAL_DATE_TIME_FORMAT,
      source = "resource.creationTime"
    ),
      @Mapping(
          target = "lastUpdateTime",
          dateFormat = LOCAL_DATE_TIME_FORMAT,
          source = "resource.lastUpdatedTime"
      )
  })
  DeviceResourceDTO toDeviceResourceDTO(DeviceResource resource);

  List<DeviceResourceDTO> toDeviceResourcesDTO(List<DeviceResource> resources);

  ResourceDTO toResourceDTO(OmaResource omaResource);

  @InheritInverseConfiguration(name = "toDeviceResourceDTO")
  DeviceResource toEntity(DeviceResourceDTO resourceDTO);
}
