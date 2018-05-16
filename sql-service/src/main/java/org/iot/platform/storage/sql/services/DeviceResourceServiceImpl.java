package org.iot.platform.storage.sql.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.exception.NotExistException;
import org.iot.platform.storage.sql.repositories.DeviceResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceResourceServiceImpl implements DeviceResourceService {

  @Autowired DeviceResourceRepository deviceResourceRepository;

  @Override
  public List<DeviceResource> getResources(UUID deviceId) {
    // TODO: validate deviceId
    return deviceResourceRepository.findResourcesWithDeviceId(deviceId);
  }

  @Override
  public DeviceResource getResource(UUID resourceId) {
    return deviceResourceRepository.findById(resourceId).get();
  }

  @Override
  public List<DeviceResource> getResources(UUID deviceId, int smartObjectId) {
    // TODO: add validation
    return deviceResourceRepository.findResourcesWithDeviceIdAndSmartObject(
        deviceId, smartObjectId);
  }

  @Override
  public List<DeviceResource> getResources(UUID deviceId, int smartObjectId, int instanceId) {
    // TODO: add validation
    return deviceResourceRepository.findResourcesWithDeviceIdAndSmartObjectAndInstanceId(
        deviceId, smartObjectId, instanceId);
  }

  @Override
  public DeviceResource saveOrUpdate(DeviceResource resource) {
    return deviceResourceRepository.save(resource);
  }

  @Override
  public void removeResource(UUID resourceId) throws NotExistException {
    Optional<DeviceResource> resource = deviceResourceRepository.findById(resourceId);
    if (!resource.isPresent())
      throw new NotExistException("Device type with Id {} does not exist", resourceId);

    deviceResourceRepository.delete(resource.get());
  }

  @Override
  public void removeReource(UUID deviceId, int smartObject, int instanceId, int omaResourceId) {
    deviceResourceRepository.removeResourceFromDevice(deviceId,omaResourceId,smartObject,instanceId);
  }

  @Override
  public ResourceValue getValue(UUID resourceId) {
    DeviceResource resource = getResource(resourceId);
    return new ResourceValue().getValue(resource.getBytValue(),resource.getResource().getType());
  }

  @Override
  public void setValue(UUID resourceId, ResourceValue value) {
    DeviceResource resource = getResource(resourceId);
    resource.setBytValue(value.convert2ByteArray(resource.getResource().getType()));

    deviceResourceRepository.save(resource);

  }


}
