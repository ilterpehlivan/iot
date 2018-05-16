package org.iot.platform.storage.sql.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.eclipse.leshan.core.model.ResourceModel;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.repositories.ResourceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResourceServiceImpl implements ResourceService {

  @Autowired ResourceRepository repository;
  public static final Logger logger = LoggerFactory.getLogger(ResourceServiceImpl.class);

  @Override
  public List<OmaResource> getById(int id) {
    // There may be multiple resources with same Id
    return repository.findResourcesWithOmaId(id);
  }

  @Override
  public List<OmaResource> listAll() {
    List<OmaResource> omaResources = new ArrayList<>();
    repository.findAll().forEach(omaResources::add);
    return omaResources;
  }

  @Override
  public OmaResource saveOrUpdate(OmaResource resource) {
    //TODO: check if there is another way to optimize this part
    OmaResource updatedResource = fetchEntityIfAlreadyExists(resource);
    return repository.save(updatedResource);
  }

  @Override
  public List<OmaResource> saveAll(List<OmaResource> objectList) {
    return objectList.stream().map(r -> this.saveOrUpdate(r)).collect(Collectors.toList());
  }

  @Override
  @Deprecated
  public OmaResource saveOrUpdateResourceModel(ResourceModel resModel) {
//    return this.saveOrUpdate(Util.convertResourceModel2Resource(resModel));
    return null;
  }

  @Override
  @Deprecated
  public void saveAllResModel(Map<Integer, ResourceModel> resModelMap) {
//    Util.convert2ResourceList(resModelMap, smartObject).forEach(r -> this.saveOrUpdate(r));
  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }

  @Override
  public boolean isResourceExist(ResourceModel resource) {
    List<OmaResource> omaResourcesFromDb = this.getById(resource.id);
    return omaResourcesFromDb != null && omaResourcesFromDb.size() > 0;
  }

  @Override
  public OmaResource fetchEntityIfAlreadyExists(OmaResource resource) {

    if (resource.getSmartobject() != null) {
      logger.debug(
          "Checking if resource entity exists with object->{} and ResourceId->{}",
          resource.getSmartobject().getId(),
          resource.getResourceId());

      OmaResource existingResource = null;
      existingResource =
          repository.findResourceWithOmaIdAndResourceId(
              resource.getSmartobject().getId(), resource.getResourceId());
      // This will garantee the Merge instead of Insert
      if (existingResource != null) {
        logger.debug(
            "Resource {} already exists in DB with Id: {} . Updating the existing resource.. ",
            existingResource.getResourceId(),
            existingResource.getId());
        Util.copyBeanProperties(resource, existingResource);
        return existingResource;
      }
    }

    return resource;
  }

  @Override
  public List<OmaResource> findResourcesWithSmartObject(Integer objectId) {
    return repository.findResourcesWithObjectId(objectId);
  }
}
