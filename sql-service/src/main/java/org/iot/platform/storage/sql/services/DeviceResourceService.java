package org.iot.platform.storage.sql.services;

import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.exception.NotExistException;

public interface DeviceResourceService {
  public List<DeviceResource> getResources(UUID deviceId);

  public DeviceResource getResource(UUID resourceId);

  public List<DeviceResource> getResources(UUID deviceId, int smartObjectId);

  public List<DeviceResource> getResources(UUID deviceId, int smartObjectId, int instanceId);

  public DeviceResource saveOrUpdate(DeviceResource resource);

  public void removeResource(UUID resourceId) throws NotExistException;

  public void removeReource(UUID deviceId, int smartObject,int instanceId, int omaResourceId);

  public ResourceValue getValue(UUID resourceId);

  public void setValue(UUID resourceId,ResourceValue value);
}
