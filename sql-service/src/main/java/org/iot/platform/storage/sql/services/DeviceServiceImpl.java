package org.iot.platform.storage.sql.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import org.iot.platform.storage.sql.domain.Device;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.repositories.DeviceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceServiceImpl implements DeviceService {

  @Autowired DeviceRepository deviceRepo;

  @Override
  public List<Device> listAll() {
    List<Device> devices = new ArrayList<>();
    deviceRepo.findAll().forEach(devices::add);
    return devices;
  }

  @Override
  public Device findById(UUID id) {
    return deviceRepo.findById(id).orElse(null);
  }

  @Override
  public Device findDeviceWithEPId(String epId) {
    Device device = deviceRepo.findDeviceWithEpId(epId);
    return device;
  }

  @Override
  public Device findDeviceWithOwnerId(String id) {
    // TODO: unimplemented
    return null;
  }

  @Override
  public Device findDeviceWithOwnerIdAndEndPoint(String endpointId, String ownerId) {
    // TODO: unimplemented
    return null;
  }

  @Override
  public List<Device> findDevicesWithType(UUID typeId) {
    // TODO: add validation
    return deviceRepo.findDevicesWithType(typeId);
  }

  @Override
  public Device saveOrUpdate(Device device) {
    return deviceRepo.save(device);
  }

  @Override
  public Device saveOrUpdate(Device device, boolean autoCreateResources) {
    if (autoCreateResources) {
      // First update resources in the device entity
      device.setResources(createDeviceResources(device, device.getType()));
    }
    return saveOrUpdate(device);
  }

  @Autowired ResourceService resourceService;

  private List<DeviceResource> createDeviceResources(Device device, DeviceType type) {
    List<DeviceResource> deviceResources =
        type.getSmartobjects()
            .stream()
            .flatMap(
                o ->
                    resourceService
                        .findResourcesWithSmartObject(o.getId())
                        .stream()
                        .map(
                            r -> {
                              return createDeviceResource(device, o, r);
                            }))
            .collect(Collectors.toList());

    return deviceResources;
  }

  private DeviceResource createDeviceResource(Device device, SmartObject o, OmaResource r) {
    DeviceResource resource = new DeviceResource();
    resource.setResource(r);
    resource.setInstanceId(0);
    resource.setCreationTime(LocalDateTime.now());
    resource.setSmartobject(o);
    resource.setDevice(device);
    resource.setLastUpdatedTime(LocalDateTime.now());

    return resource;
  }

  @Override
  public void addResource2Device(UUID deviceId, DeviceResource resource) {
    Device device = findById(deviceId);
    device.getResources().add(resource);
    saveOrUpdate(device);
  }

  @Override
  public void removeResourceFromDevice(UUID deviceId, UUID resourceId) {
    Device device = findById(deviceId);
    device.getResources().removeIf(r -> r.getId().equals(resourceId));
    saveOrUpdate(device);
  }

  @Override
  public void delete(UUID id) {
    deviceRepo.deleteById(id);
  }

  @Override
  public void deleteByEpId(String epId) {
    deviceRepo.deleteByEpId(epId);
  }
}
