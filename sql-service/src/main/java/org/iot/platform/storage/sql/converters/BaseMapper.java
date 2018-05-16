package org.iot.platform.storage.sql.converters;

import java.util.UUID;
import org.mapstruct.Mapper;

@Mapper(imports = UUID.class, componentModel = "spring")
public interface BaseMapper {
  default java.util.UUID map(java.lang.String value) {
    if (value == null) return null;
    return UUID.fromString(value);
  }
}
