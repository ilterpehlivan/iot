package org.iot.platform.storage.sql.services;

import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.eclipse.leshan.core.model.ObjectModel;

import java.util.List;

public interface SmartObjectService {
  SmartObject getById(int id);

  List<SmartObject> listAll();

  SmartObject saveOrUpdate(SmartObject object);

  void saveAll(List<SmartObject> objectList);

  /* Leshan dto objects update to DB*/
  SmartObject saveOrUpdateObjModel(ObjectModel objModel);

  void saveAllObjModel(List<ObjectModel> objModelList);

  void delete(int id);

  boolean isObjectExist(ObjectDTO object);

  OmaResource addResource(SmartObject smartObject, OmaResource resource);
}
