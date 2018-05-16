package org.iot.platform.storage.sql.services;

import org.iot.platform.storage.sql.domain.OmaResource;
import org.eclipse.leshan.core.model.ResourceModel;

import java.util.List;
import java.util.Map;

public interface ResourceService {
  List<OmaResource> getById(int id);

  List<OmaResource> listAll();

  OmaResource saveOrUpdate(OmaResource object);

  List<OmaResource> saveAll(List<OmaResource> objectList);

  /* leshan resource objects update to DB*/
  OmaResource saveOrUpdateResourceModel(ResourceModel resModel);

  void saveAllResModel(Map<Integer, ResourceModel> resModelMap);

  void delete(int id);

  boolean isResourceExist(ResourceModel resource);

  public OmaResource fetchEntityIfAlreadyExists(OmaResource resource);

  List<OmaResource> findResourcesWithSmartObject(Integer objectId);
}
