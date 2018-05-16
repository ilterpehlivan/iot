package org.iot.platform.storage.sql.converters;

import java.util.List;
import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
  uses = {BaseMapper.class, OmaResourceMapper.class},
  componentModel = "spring"
)
public interface SmartObjectMapper {
  SmartObjectMapper MAPPER = Mappers.getMapper(SmartObjectMapper.class);

  ObjectDTO toObjectDTO(SmartObject object);

  List<ObjectDTO> toObjectDTOs(List<SmartObject> objects);

  SmartObject toEntity(ObjectDTO dto);
}
