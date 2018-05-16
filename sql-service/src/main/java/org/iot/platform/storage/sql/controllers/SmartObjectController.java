package org.iot.platform.storage.sql.controllers;

import java.util.List;
import org.iot.platform.storage.sql.common.RestMediaType;
import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.converters.OmaResourceMapper;
import org.iot.platform.storage.sql.converters.SmartObjectMapper;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.services.SmartObjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/sql/oma/objects")
public class SmartObjectController {

  @Autowired private SmartObjectService smartObjectService;
  @Autowired SmartObjectMapper smartObjectMapper;

  public static final Logger logger = LoggerFactory.getLogger(SmartObjectController.class);

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<ObjectDTO>> listAllObjects() {
    List<ObjectDTO> objects = smartObjectMapper.toObjectDTOs(smartObjectService.listAll());
    if (objects == null || objects.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(objects.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<ObjectDTO>>(objects, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<ObjectDTO> getObject(@PathVariable("id") int id) {
    SmartObject smartObjectServiceById = smartObjectService.getById(id);
    if (smartObjectServiceById == null) {
      logger.error("Smart Object with id {} not found.", id);
      return new ResponseEntity(
          new CustomErrorType("Smart Object with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }
    ObjectDTO objectDTO = smartObjectMapper.toObjectDTO(smartObjectServiceById);
    return new ResponseEntity<ObjectDTO>(objectDTO, HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<?> createObject(
      @RequestBody ObjectDTO object, UriComponentsBuilder ucBuilder) {
    logger.info("Creating Smart Object: {}", object);

    if (smartObjectService.isObjectExist(object)) {
      logger.error("Unable to create. A smart Object with id {} already exist", object.getId());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to create. A resource with id " + object.getId() + " already exist."),
          HttpStatus.CONFLICT);
    }

    smartObjectService.saveOrUpdate(object.convert2EntityObject());
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        ucBuilder.path("/api/v1/oma/objects/{id}").buildAndExpand(object.getId()).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }

  @Autowired OmaResourceMapper resourceMapper;

  @RequestMapping(value = "/{id}/resource/", method = RequestMethod.PUT)
  public ResponseEntity<?> putResource(
      @PathVariable("id") int id, @RequestBody ResourceDTO resource) {
    logger.info("Adding new OmaResource : {}", resource);

    SmartObject smartObjectServiceById = smartObjectService.getById(id);
    if (smartObjectServiceById == null) {
      logger.error("Smart Object with id {} not found.", id);
      return new ResponseEntity(
          new CustomErrorType("Smart Object with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }

    OmaResource entity = resourceMapper.toEntity(resource);
    OmaResource result = null;
    try {
      result = smartObjectService.addResource(smartObjectServiceById, entity);
    } catch (Exception e) {
      logger.error("Unable to create. A OmaResource with id {} already exist", resource.getId());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to create. A resource with id " + resource.getId() + " already exist."),
          HttpStatus.CONFLICT);
    }
    ResourceDTO response = resourceMapper.toDto(result);
    return new ResponseEntity<ResourceDTO>(response, HttpStatus.CREATED);
  }

  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.PATCH,
    consumes = RestMediaType.APPLICATION_MERGE_PATCH_JSON_VALUE
  )
  public ResponseEntity<?> updateResource(
      @PathVariable("id") int id, @RequestBody ObjectDTO model) {
    logger.info("Updating smart object : {}", model);

    SmartObject existingObject = smartObjectService.getById(id);

    if (existingObject == null) {
      logger.error("Unable to update. A smart object with id {} does not exist", model.getId());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to update. A smart object with id " + model.getId() + " does not  exist."),
          HttpStatus.NOT_FOUND);
    }

    SmartObject convertedObject = smartObjectMapper.toEntity(model);

    // TODO: do the validation
    Util.copyBeanProperties(convertedObject, existingObject);

    logger.info("Copied object -> {}", existingObject);
    smartObjectService.saveOrUpdate(existingObject);
    logger.info("Saved the smart object {}", existingObject);
    ObjectDTO response = smartObjectMapper.toObjectDTO(existingObject);
    return new ResponseEntity<ObjectDTO>(response, HttpStatus.CREATED);
  }

  //  private boolean copyRestModel2ExistingEntity(ObjectDTO toBePatched, SmartObject
  // existingObject) {
  //    try {
  //      new BeanUtilsBean() {
  //
  //        @Override
  //        public void copyProperty(Object dest, String name, Object value)
  //            throws IllegalAccessException, InvocationTargetException {
  //          logger.info("CopyProperty->Name:{} and Value:{}", name, value);
  //          if (value == null || "resourceId".equalsIgnoreCase(name) ||
  // "id".equalsIgnoreCase(name)) {
  //            return;
  //          }
  //          if (value instanceof List && ((List) value).get(0) instanceof ResourceDTO) {
  //            for (ResourceDTO restModel : (List<ResourceDTO>) value) {
  //              logger.info("**INside the OmaResource Section ***");
  //              SmartObject currentEntity = (SmartObject) dest;
  //              Optional<OmaResource> optionalResource =
  //                  currentEntity
  //                      .getOmaResources()
  //                      .stream()
  //                      .filter(r -> r.getResourceId() == restModel.getId())
  //                      .findFirst();
  //              logger.info("This is the resutl of query->{}", optionalResource);
  //              if (optionalResource.isPresent()) { // Patch current OmaResource
  //                logger.info(
  //                    "Patching existing resource:{} , with {}",
  //                    optionalResource.get().getResourceId(),
  //                    value);
  //                copyProperties(optionalResource.get(), value);
  //              } else {
  //                // Add additional resource
  //                logger.info("Adding new resource: {} into {}", value, currentEntity.getId());
  //                OmaResource convert2EntityOmaResource = restModel.convert2EntityResource();
  //                convert2EntityOmaResource.setSmartobject(currentEntity);
  //                currentEntity.getOmaResources().add(convert2EntityOmaResource);
  //              }
  //            }
  //          } else { // default
  //            super.copyProperty(dest, name, value);
  //          }
  //        }
  //      }.copyProperties(existingObject, toBePatched);
  //
  //    } catch (Exception e) {
  //      logger.error("Exception occured during the copying body into resource {}",
  // e.getMessage());
  //      return false;
  //    }
  //
  //    return true;
  //  }
}
