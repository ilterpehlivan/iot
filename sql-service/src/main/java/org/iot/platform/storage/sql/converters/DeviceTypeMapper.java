package org.iot.platform.storage.sql.converters;

import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
  imports = UUID.class,
  uses = {BaseMapper.class},
  componentModel = "spring"
)
public interface DeviceTypeMapper {

  DeviceTypeMapper MAPPER = Mappers.getMapper(DeviceTypeMapper.class);

  @Mappings({
    @Mapping(target = "id", expression = "java(  type.getId().toString() )"),
    @Mapping(target = "supportedObjects", source = "type.smartobjects"),
    @Mapping(target = "supportedProtocols", source = "type.supportedprotocols")
  })
  DeviceTypeDTO toDeviceTypeDTO(DeviceType type);

  List<DeviceTypeDTO> toDeviceTypeDTOs(List<DeviceType> types);

  @InheritInverseConfiguration(name = "toDeviceTypeDTO")
  DeviceType toEntity(DeviceTypeDTO dto);
}
