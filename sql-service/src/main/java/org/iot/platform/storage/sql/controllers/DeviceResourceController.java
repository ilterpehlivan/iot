package org.iot.platform.storage.sql.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.iot.platform.storage.sql.common.RestMediaType;
import org.iot.platform.storage.sql.controllers.dto.DeviceResourceDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.converters.DeviceResourceMapper;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.Device;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.services.DeviceResourceService;
import org.iot.platform.storage.sql.services.DeviceService;
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

@CrossOrigin
@Controller
@RequestMapping("/api/v1/sql/resource")
public class DeviceResourceController {

  @Autowired DeviceResourceService resourceService;

  @Autowired DeviceResourceMapper deviceResourceMapper;

  @Autowired DeviceService deviceService;

  public static final Logger logger = LoggerFactory.getLogger(DeviceResourceController.class);

  @RequestMapping(value = "/device/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<DeviceResourceDTO>> listAllResources(
      @RequestParam(required = false) Map<String, String> query, @PathVariable("id") String id) {

    Device existingDevice = deviceService.findById(UUID.fromString(id));
    if (existingDevice == null) {
      logger.error("Unable to find device device. It does not exists {}", id);
      return new ResponseEntity(
          new CustomErrorType("Unable to find device. It does not exists with this id " + id),
          HttpStatus.NOT_FOUND);
    }

    if (query != null) { // Query operations
      logger.info("Query params: {}", query);
      // TODO: add filters
    }

    List<DeviceResource> resources = resourceService.getResources(UUID.fromString(id));
    List<DeviceResourceDTO> result = deviceResourceMapper.toDeviceResourcesDTO(resources);
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(result.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<DeviceResourceDTO>>(result, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<DeviceResourceDTO> getResource(@PathVariable(name = "id") String id) {
    DeviceResource resource = resourceService.getResource(UUID.fromString(id));
    DeviceResourceDTO result = deviceResourceMapper.toDeviceResourceDTO(resource);
    return new ResponseEntity<DeviceResourceDTO>(result, HttpStatus.OK);
  }

  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.PATCH,
    consumes = RestMediaType.APPLICATION_PATCH_JSON_VALUE
  )
  public ResponseEntity<?> updateResourcePartial(
      @PathVariable("id") String id, @RequestBody DeviceResourceDTO resource) {
    DeviceResource existingResource = resourceService.getResource(UUID.fromString(id));
    if (existingResource == null) {
      logger.error("Unable to update resource. It does not exists {}", id);
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to update the resource. It does not exists with this id " + id),
          HttpStatus.NOT_FOUND);
    }

    DeviceResource updateEntity = deviceResourceMapper.toEntity(resource);
    logger.debug(
        "Copying the update entity ->{} into Existing entity -> {}",
        updateEntity,
        existingResource);

    Util.copyBeanProperties(updateEntity, existingResource);
    logger.debug("Updated the existing Resource ->{}", existingResource);
    DeviceResource updatedResource = resourceService.saveOrUpdate(existingResource);

    return new ResponseEntity<DeviceResourceDTO>(
        deviceResourceMapper.toDeviceResourceDTO(updatedResource), HttpStatus.CREATED);
  }



  @RequestMapping(
      value = "/{id}/value",
      method = RequestMethod.PUT
  )
  public ResponseEntity<?> updateResourceValue(@PathVariable("id") String id, @RequestBody ResourceValue value){
    DeviceResource existingResource = resourceService.getResource(UUID.fromString(id));
    if (existingResource == null) {
      logger.error("Unable to update resource. It does not exists {}", id);
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to update the resource. It does not exists with this id " + id),
          HttpStatus.NOT_FOUND);
    }

    logger.debug("Updating resource {} with value {}",id,value);
    existingResource.setBytValue(value.convert2ByteArray());

    DeviceResource updatedResource = resourceService.saveOrUpdate(existingResource);
    logger.debug("Updated resource {}",updatedResource);

    return new ResponseEntity<DeviceResourceDTO>(
        deviceResourceMapper.toDeviceResourceDTO(updatedResource), HttpStatus.CREATED);

  }

}
