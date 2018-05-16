package org.iot.platform.storage.sql.services;

import org.iot.platform.storage.sql.domain.Device;

import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.domain.DeviceResource;

public interface DeviceService {
  List<Device> listAll();

  Device findById(UUID id);

  Device findDeviceWithEPId(String epId);

  Device findDeviceWithOwnerId(String id);

  Device findDeviceWithOwnerIdAndEndPoint(String endpointId, String ownerId);

  List<Device> findDevicesWithType(UUID typeId);

  Device saveOrUpdate(Device device);

  /* optional to create resources from device type*/
  Device saveOrUpdate(Device device,boolean autoCreateResources);

  void addResource2Device(UUID deviceId, DeviceResource resource);

  void removeResourceFromDevice(UUID deviceId, UUID resourceId);

  void delete(UUID id);

  void deleteByEpId(String epId);
}
