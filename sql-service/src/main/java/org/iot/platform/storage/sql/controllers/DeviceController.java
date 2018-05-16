package org.iot.platform.storage.sql.controllers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.iot.platform.storage.sql.common.RestMediaType;
import org.iot.platform.storage.sql.controllers.dto.DeviceDTO;
import org.iot.platform.storage.sql.controllers.dto.DeviceResourceDTO;
import org.iot.platform.storage.sql.converters.DeviceMapper;
import org.iot.platform.storage.sql.converters.DeviceResourceMapper;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.Device;
import org.iot.platform.storage.sql.domain.DeviceResource;
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
import org.springframework.web.util.UriComponentsBuilder;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/sql/device")
public class DeviceController {

  @Autowired DeviceService deviceService;

  @Autowired DeviceMapper deviceMapper;

  public static final Logger logger = LoggerFactory.getLogger(DeviceController.class);

  @RequestMapping(value = "", method = RequestMethod.GET)
  public ResponseEntity<List<DeviceDTO>> listAllDevices(
      @RequestParam(required = false) Map<String, String> query) {
    if (query != null) { // Query operations
      logger.info("Query params: {}", query);
      // TODO: add filters
    }

    List<DeviceDTO> result = deviceMapper.toDeviceDTOs(deviceService.listAll());

    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(result.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<DeviceDTO>>(result, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<DeviceDTO> getDevice(@PathVariable(name = "id") String id) {
    Device device = deviceService.findById(UUID.fromString(id));
    if (device == null) {
      return new ResponseEntity(
          new CustomErrorType("The device with Id:" + id + " Not found"), HttpStatus.NOT_FOUND);
    }

    DeviceDTO result = deviceMapper.toDeviceDTO(device);
    return new ResponseEntity<DeviceDTO>(result, HttpStatus.OK);
  }

  @RequestMapping(value = "/type/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<DeviceDTO>> getDeviceswithType(@PathVariable(name = "id") String id) {
    List<Device> devicesWithType = deviceService.findDevicesWithType(UUID.fromString(id));
    if (devicesWithType == null || devicesWithType.isEmpty()) {
      return new ResponseEntity(
          new CustomErrorType("The devices with type:" + id + " Not found"), HttpStatus.NOT_FOUND);
    }

    List<DeviceDTO> result = deviceMapper.toDeviceDTOs(devicesWithType);
    HttpHeaders headers = new HttpHeaders();
    headers.set("Content-Range", String.valueOf(result.size()));
    headers.set("Access-Control-Expose-Headers", "Content-Range");
    return new ResponseEntity<List<DeviceDTO>>(result, headers, HttpStatus.OK);
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public ResponseEntity<?> createDevice(
      @RequestBody DeviceDTO deviceDTO, UriComponentsBuilder ucBuilder) {

    // TODO: add validation to content
    if (deviceDTO.getDeviceTypeModel() == null) {
      logger.error("Device type cannot be empty");
      return new ResponseEntity(
          new CustomErrorType("Device type cannot be empty"), HttpStatus.BAD_REQUEST);
    }
    if (deviceService.findDeviceWithEPId(deviceDTO.getName()) != null) {
      logger.error("Unable to create. A device with name {} already exist", deviceDTO.getName());
      return new ResponseEntity(
          new CustomErrorType(
              "Unable to create. A device with name " + deviceDTO.getName() + " already exist."),
          HttpStatus.CONFLICT);
    }

    Device device = deviceMapper.toEntity(deviceDTO);
    logger.debug("Received device to create: {}", device);
    Device savedDevice = null;
    if (device.getResources() != null && device.getResources().size() > 0) {
      // TODO: remove the resources which are not supported by Device Type
      savedDevice = deviceService.saveOrUpdate(device);
    } else {
      // Auto generate resources from the device type
      savedDevice = deviceService.saveOrUpdate(device, true);
    }

    DeviceDTO result = deviceMapper.toDeviceDTO(savedDevice);

    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(
        ucBuilder.path("/api/v1/sql/device/{id}").buildAndExpand(savedDevice.getId()).toUri());
    return new ResponseEntity<DeviceDTO>(result, headers, HttpStatus.CREATED);
  }

  @RequestMapping(
    value = "/{id}",
    method = RequestMethod.PATCH,
    consumes = RestMediaType.APPLICATION_MERGE_PATCH_JSON_VALUE
  )
  public ResponseEntity<?> updateResource(
      @PathVariable("id") String id, @RequestBody DeviceDTO deviceDTO) {

    Device existingDevice = deviceService.findById(UUID.fromString(id));
    if (existingDevice == null) {
      logger.error("Unable to update device. It does not exists {}", id);
      return new ResponseEntity(
          new CustomErrorType("Unable to update the device. It does not exists with this id " + id),
          HttpStatus.NOT_FOUND);
    }

    Device updateDevice = deviceMapper.toEntity(deviceDTO);
    Util.copyBeanProperties(updateDevice, existingDevice);
    logger.debug("Copied into existing device {}", existingDevice);

    Device updatedDevice = deviceService.saveOrUpdate(existingDevice);
    logger.debug("Updated Device is succesfully saved");

    return new ResponseEntity<DeviceDTO>(
        deviceMapper.toDeviceDTO(updatedDevice), HttpStatus.CREATED);
  }

  @Autowired DeviceResourceMapper deviceResourceMapper;

  @RequestMapping(value = "/{id}/resources", method = RequestMethod.PUT)
  public ResponseEntity<?> addResource(
      @PathVariable("id") String id, @RequestBody DeviceResourceDTO resourceDTO) {
    DeviceResource entity = deviceResourceMapper.toEntity(resourceDTO);
    deviceService.addResource2Device(UUID.fromString(id), entity);
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public ResponseEntity<?> deleteDevice(@PathVariable(name = "id") String id) {
    deviceService.delete(UUID.fromString(id));
    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }
}
