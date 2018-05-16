package org.iot.platform.storage.sql.controllers;

import static org.iot.platform.storage.sql.converters.Util.convert2ResourceModelList;
import static org.iot.platform.storage.sql.converters.Util.copyBeanProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.eclipse.leshan.core.model.ResourceModel;
import org.iot.platform.storage.sql.common.RestMediaType;
import org.iot.platform.storage.sql.controllers.dto.ResourceDTO;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.services.ResourceService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/api/v1/sql/oma/resources")
public class ResourceController {
  @Autowired private ResourceService resourceService;
  @Autowired private SmartObjectService smartObjectService;

  public static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

  @CrossOrigin
  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<ResourceModel>> listAllResources(
      @RequestParam(required = false) Map<String, String> query) {

    // TODO: paging or query options here
    List<ResourceModel> resources = new ArrayList<>();
    resources = convert2ResourceModelList(resourceService.listAll());
    return getResourceListResponseEntity(resources);
  }

  public ResponseEntity<List<ResourceModel>> getResourceListResponseEntity(
      List<ResourceModel> resources) {
    if (resources.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(resources.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<ResourceModel>>(resources, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<ResourceModel>> getResource(@PathVariable("id") int id) {
    List<OmaResource> omaResourceServiceById = resourceService.getById(id);
    if (omaResourceServiceById == null || omaResourceServiceById.isEmpty()) {
      logger.error("OmaResource with id {} not found.", id);
      return new ResponseEntity(
          new CustomErrorType("OmaResource with id " + id + " not found"), HttpStatus.NOT_FOUND);
    }
    List<ResourceModel> resources = convert2ResourceModelList(omaResourceServiceById);
    return getResourceListResponseEntity(resources);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<?> createResource(
      @RequestBody ResourceModel resource, UriComponentsBuilder ucBuilder) {
    logger.info("Creating OmaResource: {}", resource);

    if (resourceService.isResourceExist(resource)) {
      logger.error("Unable to create. A resource with id {} already exist", resource.id);
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to create. A resource with id " + resource.id + " already exist."),
          HttpStatus.CONFLICT);
    }

    resourceService.saveOrUpdateResourceModel(resource);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        ucBuilder.path("/api/v1/oma/resources/{id}").buildAndExpand(resource.id).toUri());
    return new ResponseEntity<String>(headers, HttpStatus.CREATED);
  }

  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.PATCH,
    consumes = RestMediaType.APPLICATION_MERGE_PATCH_JSON_VALUE
  )
  public ResponseEntity<?> updateResource(
      @PathVariable("id") int id, @RequestBody ResourceDTO model) {
    logger.info("Updating resource : {}", model);

    List<OmaResource> existingOmaResources = resourceService.getById(id);
    if (existingOmaResources == null || existingOmaResources.isEmpty()) {
      logger.error("Unable to update. A resource with id {} does not exist", model.getId());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to update. A resource with id " + model.getId() + " does not  exist."),
          HttpStatus.NOT_FOUND);
    } else {
      if (existingOmaResources.size() > 1) {
        logger.error("Multiple resources with same Id {} ", model.getId());
        return new ResponseEntity(
            new CustomErrorType(
                "Unable to update. Multiple resources with same Id: "
                    + model.getId()
                    + ". Use smartObject update API instead"),
            HttpStatus.MULTIPLE_CHOICES);
      }
    }

    OmaResource updatedOmaResource = existingOmaResources.get(0);
    if (!copyBeanProperties(model, updatedOmaResource))
      return new ResponseEntity(
          new CustomErrorType("Unable to update. Internal Error"),
          HttpStatus.INTERNAL_SERVER_ERROR);

    resourceService.saveOrUpdate(updatedOmaResource);
    logger.info("Saved the resource {}", updatedOmaResource);
    ResourceDTO response = ResourceDTO.convertResourceEntity(updatedOmaResource);
    return new ResponseEntity<ResourceDTO>(response, HttpStatus.CREATED);
  }
}
