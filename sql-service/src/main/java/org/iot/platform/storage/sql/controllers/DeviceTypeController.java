package org.iot.platform.storage.sql.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.converters.DeviceTypeMapper;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.services.DeviceTypeService;
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

@CrossOrigin
@Controller
@RequestMapping("/api/v1/sql/devicetype")
public class DeviceTypeController {

  @Autowired DeviceTypeService typeService;

  @Autowired DeviceTypeMapper typeMapper;

  public static final Logger logger = LoggerFactory.getLogger(DeviceTypeController.class);

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<DeviceTypeDTO>> listAllTypes(
      @RequestParam(required = false) Map<String, String> query) {

    List<DeviceType> types = new ArrayList<>();
    if (query != null) { // Query operations
      logger.info("Query params: {}", query);
      // TODO: add filters
    }

    types = typeService.listAll();
    if (types.isEmpty()) {
      return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    List<DeviceTypeDTO> result = typeMapper.toDeviceTypeDTOs(types);
    //    try {
    //      result = BaseModelConverter.getConverter(DeviceType.class).convert2RestModel(types);
    //    } catch (CloneNotSupportedException e) {
    //      logger.error("Internal Error:" + e.getMessage());
    //      return new ResponseEntity(
    //          new CustomErrorType("Internal error"), HttpStatus.INTERNAL_SERVER_ERROR);
    //    }
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(result.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<DeviceTypeDTO>>(result, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<DeviceTypeDTO> getDeviceType(@PathVariable(name = "id") String id) {
    UUID uId;
    try {
      uId = UUID.fromString(id);
    } catch (IllegalArgumentException e) {
      return new ResponseEntity(
          new CustomErrorType("Illegal format Id " + id), HttpStatus.BAD_REQUEST);
    }
    DeviceType type = typeService.findDeviceType(uId);
    if (type == null) {
      return new ResponseEntity(
          new CustomErrorType("The type with Id:" + id + " Not found"), HttpStatus.NOT_FOUND);
    }
    DeviceTypeDTO result = typeMapper.toDeviceTypeDTO(type);
    return new ResponseEntity<DeviceTypeDTO>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<?> createDeviceType(
      @RequestBody DeviceTypeDTO typeRest, UriComponentsBuilder ucBuilder) {
    logger.info("Creating Device Type: {}", typeRest.getName());

    DeviceType type = typeService.findDeviceType(typeRest.getName());
    if (type != null) {
      logger.error("Unable to create. A type with name {} already exist", type.getName());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to create. A device type with name " + type.getName() + " already exist."),
          HttpStatus.CONFLICT);
    }

    DeviceType entity = typeMapper.toEntity(typeRest);
    DeviceType createdDevice = typeService.createDeviceType(entity);
    DeviceTypeDTO result = typeMapper.toDeviceTypeDTO(createdDevice);

    logger.debug("Saved Device Type: {}",createdDevice);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        ucBuilder
            .path("/api/v1/sql/devicetype/{id}")
            .buildAndExpand(createdDevice.getId())
            .toUri());
    return new ResponseEntity<DeviceTypeDTO>(result, headers, HttpStatus.CREATED);
  }
}
