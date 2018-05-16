package org.iot.platform.storage.sql.converters;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.eclipse.leshan.core.model.ObjectModel;
import org.eclipse.leshan.core.model.ResourceModel;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Util {

  public static final Logger logger = LoggerFactory.getLogger(Util.class);

  public static Map<SmartObject, List<OmaResource>> convertLeshanObjects2Entities(
      List<ObjectModel> objects) {
    Map<SmartObject, List<OmaResource>> entityMap = new HashMap<>();

    for (ObjectModel model : objects) {
      SmartObject smartObject = convertObjModel2SmartObject(model);
      List<OmaResource> resources = convert2ResourceList(model.resources, smartObject);
      entityMap.put(smartObject,resources);
    }

    return entityMap;
  }

  /**
   * Functions to support the leshan dto into Entities
   *
   * @param objects
   * @return
   */
  public static List<SmartObject> convertObjeModel2SmartObjectList(List<ObjectModel> objects) {
    if (objects == null) return null;
    List<SmartObject> smartObjList = new ArrayList<>();
    objects.forEach(o -> smartObjList.add(convertObjModel2SmartObject(o)));
    return smartObjList;
  }

  public static SmartObject convertObjModel2SmartObject(ObjectModel objModel) {
    SmartObject smartObject =
        new SmartObject(
            objModel.id,
            objModel.name,
            objModel.description,
            objModel.version,
            objModel.multiple,
            objModel.mandatory);
    return smartObject;
  }

  public static List<OmaResource> convert2ResourceList(
      Map<Integer, ResourceModel> resources, SmartObject smartObject) {

    List<OmaResource> omaResourceList =
        resources
            .values()
            .stream()
            .map(
                r -> {
                  OmaResource resource =
                      new OmaResource(
                          r.id,
                          r.name,
                          r.operations,
                          r.multiple,
                          r.mandatory,
                          r.type,
                          r.rangeEnumeration,
                          r.units,
                          r.description);
                  resource.setSmartobject(smartObject);
                  return resource;
                })
            .collect(Collectors.toList());

    return omaResourceList;
  }

  public static OmaResource convertResourceModel2Resource(ResourceModel r) {
    return new OmaResource(
        r.id,
        r.name,
        r.operations,
        r.multiple,
        r.mandatory,
        r.type,
        r.rangeEnumeration,
        r.units,
        r.description);
  }

  public static List<ResourceModel> convert2ResourceModelList(List<OmaResource> omaResources) {
    return omaResources.stream().map(r -> convertResource2Model(r)).collect(Collectors.toList());
  }

  public static ResourceModel convertResource2Model(OmaResource r) {
    return new ResourceModel(
        r.getId(),
        r.getName(),
        r.getOperations(),
        r.isMultiple(),
        r.isMandatory(),
        r.getType(),
        r.getRangeEnumeration(),
        r.getUnits(),
        r.getDescription());
  }

  //  /** Handling the REST dto transformation */
  //
  //  /** Convert the Entities into This object */
  //  public static List<ObjectDTO> convertEntity2RestModel(List<SmartObject> objects) {
  //    return objects
  //        .stream()
  //        .map(
  //            o -> {
  //              return convertEntity2RestModel(o);
  //            })
  //        .collect(Collectors.toList());
  //  }

  //  public static ObjectDTO convertEntity2RestModel(SmartObject object) {
  //    ObjectDTO restModel = new ObjectDTO();
  //    restModel.setDescription(object.getDescription());
  //    restModel.setId(object.getId());
  //    restModel.setMandatory(object.isMandatory());
  //    restModel.setMultiple(object.isMultiple());
  //    restModel.setName(object.getName());
  //    restModel.setVersion(object.getVersion());
  //    restModel.setResources(convertResourceEntities2Models(object.getOmaResources()));
  //
  //    return restModel;
  //  }

  private static List<ResourceDTO> convertResourceEntities2Models(List resources) {
    return (List<ResourceDTO>)
        resources
            .stream()
            .map(r -> ResourceDTO.convertResourceEntity((OmaResource) r))
            .collect(Collectors.toList());
  }

  public static boolean copyBeanProperties(Object model, Object existingResource) {
    try {
      new BeanUtilsBean() {
        @Override
        public void copyProperty(Object bean, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
          logger.trace("CopyProperty->Name:{} and Value:{}", name, value);
          if (value == null) {
            return;
          }
          super.copyProperty(bean, name, value);
        }
      }.copyProperties(existingResource, model);
    } catch (Exception e) {
      logger.error("Exception occured during the copying body into resource {}", e.getMessage());
      return false;
    }
    return true;
  }
}
