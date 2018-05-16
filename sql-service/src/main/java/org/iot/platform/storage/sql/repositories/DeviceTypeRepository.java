package org.iot.platform.storage.sql.repositories;

import java.util.UUID;
import javax.transaction.Transactional;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface DeviceTypeRepository extends CrudRepository<DeviceType,UUID> {
  @Query(value = "select d from DeviceType d where d.name = :name")
  public DeviceType findDeviceTypeWithName(@Param("name") String name);
}
