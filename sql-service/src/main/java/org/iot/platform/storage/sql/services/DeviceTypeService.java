package org.iot.platform.storage.sql.services;

import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.exception.NotExistException;

public interface DeviceTypeService {
  public List<DeviceType> listAll();

  public DeviceType findDeviceType(UUID id);

  public DeviceType createDeviceType(DeviceType type);

  public DeviceType createDeviceType(DeviceTypeDTO restType);

  public DeviceType updateDeviceType(UUID id, DeviceType type) throws NotExistException;

  public DeviceType updateDeviceType(UUID id, DeviceTypeDTO restType)
      throws NotExistException;

  public List<SmartObject> getSmartObjectsOfType(UUID typeId) throws NotExistException;

  public void removeDeviceType(UUID id) throws NotExistException;

  DeviceType findDeviceType(String name);
}
