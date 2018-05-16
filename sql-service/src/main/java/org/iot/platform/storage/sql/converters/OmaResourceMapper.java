package org.iot.platform.storage.sql.converters;


import java.util.List;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(
    uses = {BaseMapper.class},
    componentModel = "spring"
)
public interface OmaResourceMapper {

  OmaResourceMapper MAPPER = Mappers.getMapper(OmaResourceMapper.class);

  ResourceDTO toDto(OmaResource resource);

  OmaResource toEntity(ResourceDTO dto);

  List<ResourceDTO> toDtos(List<OmaResource> resourceList);

}
