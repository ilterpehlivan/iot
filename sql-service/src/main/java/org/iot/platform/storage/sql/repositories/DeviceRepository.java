package org.iot.platform.storage.sql.repositories;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.iot.platform.storage.sql.domain.Device;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface DeviceRepository extends CrudRepository<Device, UUID> {

  @Query(value = "select d from Device d where d.name = :name")
  public Device findDeviceWithEpId(@Param("name") String name);

  @Query(value = "delete from Device d where d.name = :name")
  public void deleteByEpId(@Param("name") String name);

  @Query(value = "select d from Device d where d.type.id = :typeId")
  public List<Device> findDevicesWithType(@Param("type") UUID typeId);

  @Query(value = "select d from Device d where d.name like %:name%")
  public List<Device> searchDeviceWithName(@Param("name") String name);
}
