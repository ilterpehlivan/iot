package org.iot.platform.storage.sql.repositories;

import java.util.List;
import java.util.UUID;
import javax.transaction.Transactional;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Transactional
public interface DeviceResourceRepository extends CrudRepository<DeviceResource, UUID> {

  @Query(value = "select r from DeviceResource r where r.device.id = :deviceId")
  public List<DeviceResource> findResourcesWithDeviceId(@Param("deviceId") UUID deviceId);

  @Query(
    value =
        "select r from DeviceResource r where r.device.id = :deviceId AND r.resource.smartobject.id = :objectId "
  )
  public List<DeviceResource> findResourcesWithDeviceIdAndSmartObject(
      @Param("deviceId") UUID deviceId, @Param("objectId") int objectId);

  @Query(
    value =
        "select r from DeviceResource r where r.device.id = :deviceId AND r.resource.smartobject.id = :objectId AND r.instanceId = :instanceId "
  )
  public List<DeviceResource> findResourcesWithDeviceIdAndSmartObjectAndInstanceId(
      @Param("deviceId") UUID deviceId,
      @Param("objectId") int objectId,
      @Param("instanceId") int instanceId);

  @Query(
    value =
        "delete from DeviceResource r where r.device.id = :deviceId AND r.resource.resourceId = :omaResourceId AND r.resource.smartobject.id = :objectId AND r.instanceId = :instanceId"
  )
  public void removeResourceFromDevice(
      @Param("deviceId") UUID deviceId,
      @Param("omaResourceId") int omaResourceId,
      @Param("objectId") int objectId,
      @Param("instanceId") int instanceId);
}
