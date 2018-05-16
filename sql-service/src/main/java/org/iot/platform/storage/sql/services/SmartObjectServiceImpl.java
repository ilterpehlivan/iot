package org.iot.platform.storage.sql.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.leshan.core.model.ObjectModel;
import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.repositories.SmartObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

@Component
public class SmartObjectServiceImpl implements SmartObjectService {

  @Autowired SmartObjectRepository repository;

  @Override
  public SmartObject getById(int id) {
    return repository.findById(id).orElse(null);
  }

  @Override
  public List<SmartObject> listAll() {
    List<SmartObject> objects = new ArrayList<>();
    repository.findAll().forEach(objects::add);
    return objects;
  }

  @Override
  public SmartObject saveOrUpdate(SmartObject object) {
    // Firstly fetch the resources from DB and use them if they exist
    //    updateObjectResourcesIfExist(object);
    return repository.save(object);
  }

  //  private void updateObjectResourcesIfExist(SmartObject object) {
  //    List<OmaResource> updatedResources =
  //        object
  //            .getOmaResources()
  //            .stream()
  //            .map(
  //                r -> {
  //                  return resourceService.fetchEntityIfAlreadyExists(r);
  //                })
  //            .collect(Collectors.toList());
  //
  ////    List<OmaResource> listA = updatedResources;
  ////    List<OmaResource> listB = object.getOmaResources();
  ////
  ////    listA.addAll(
  ////        listB
  ////            .stream()
  ////            .filter(
  ////                t ->
  ////                    listA
  ////                        .stream()
  ////                        .noneMatch(
  ////                            s ->
  ////                                s.getResourceId() != t.getResourceId()
  ////                                    && s.getSmartobject().getId() !=
  // t.getSmartobject().getId()))
  ////            .collect(Collectors.toList()));
  //
  //    object.setOmaResources(updatedResources);
  //  }

  @Override
  public void saveAll(List<SmartObject> objectList) {
    objectList.forEach(o -> this.saveOrUpdate(o));
  }

  @Override
  public SmartObject saveOrUpdateObjModel(ObjectModel objModel) {
    return this.saveOrUpdate(Util.convertObjModel2SmartObject(objModel));
  }

  /**
   * This function stores the objects together with oma resources
   * @param objModelList
   */
  @Override
  public void saveAllObjModel(List<ObjectModel> objModelList) {

    Map<SmartObject, List<OmaResource>> objectEntityMap =
        Util.convertLeshanObjects2Entities(objModelList);

    objectEntityMap.forEach(
        (k, v) -> {
          this.saveOrUpdate(k);
          resourceService.saveAll(v);
        });

  }

  @Override
  public void delete(int id) {
    repository.deleteById(id);
  }

  @Override
  public boolean isObjectExist(ObjectDTO object) {
    SmartObject entity = getById(object.getId());
    return entity != null;
  }

  @Autowired ResourceService resourceService;

  @Override
  public OmaResource addResource(SmartObject smartObject, OmaResource resource) {
    resource.setSmartobject(smartObject);
    try {
      return resourceService.saveOrUpdate(resource);
    } catch (DataIntegrityViolationException e) {
      throw new DuplicateKeyException("Resource already exists under smart object");
    }
  }
}
