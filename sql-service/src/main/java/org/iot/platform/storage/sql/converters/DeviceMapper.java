package org.iot.platform.storage.sql.converters;

import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.DeviceDTO;
import org.iot.platform.storage.sql.domain.Device;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper(
  imports = UUID.class,
  componentModel = "spring",
  uses = {DeviceTypeMapper.class, DeviceResourceMapper.class, BaseMapper.class}
)
public interface DeviceMapper {

  String LOCAL_DATE_TIME_FORMAT = "dd.MM.yyyy HH:mm";

  DeviceMapper MAPPER = Mappers.getMapper(DeviceMapper.class);

  @Mappings({
    @Mapping(target = "id", expression = "java(  device.getId().toString() )"),
    @Mapping(target = "deviceTypeModel", source = "device.type"),
    @Mapping(target = "deviceResourceModelList", source = "device.resources"),
    @Mapping(
      target = "lastUpdateTime",
      dateFormat = LOCAL_DATE_TIME_FORMAT,
      source = "device.lastUpdatedTime"
    ),
    @Mapping(
      target = "createdTime",
      dateFormat = LOCAL_DATE_TIME_FORMAT,
      source = "device.profileCreationTime"
    )
  })
  DeviceDTO toDeviceDTO(Device device);

  List<DeviceDTO> toDeviceDTOs(List<Device> devices);

  @InheritInverseConfiguration(name = "toDeviceDTO")
  @Mappings({
    @Mapping(
      target = "status",
      expression =
          "java( deviceDTO.getStatus() == null ? Device.Status.CREATED:Device.Status.valueOf(deviceDTO.getStatus()))"
    )
  })
  Device toEntity(DeviceDTO deviceDTO);
}
