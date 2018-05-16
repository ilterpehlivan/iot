package org.iot.platform.storage.sql.rest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.iot.platform.storage.sql.common.RestMediaType;
import org.iot.platform.storage.sql.controllers.dto.DeviceDTO;
import org.iot.platform.storage.sql.controllers.dto.DeviceResourceDTO;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.domain.Protocol;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

public class TestDeviceLifeCycle extends RestBaseTestClass {

  @Test
  public void initializeTest() {
    // Verify if the objects are loaded succesfully
    ResponseEntity<List> objects =
        getRestTemplate().getForEntity("/api/v1/sql/oma/objects", List.class);

    assertThat(objects.getBody().size()).isEqualTo(3);

    ResponseEntity<List> resources =
        getRestTemplate().getForEntity("/api/v1/sql/oma/resources", List.class);
    assertThat(resources.getBody().size()).isEqualTo(37);
  }

  @Test
  public void createDeviceTypeAndDevice() {
    // First create device type
    DeviceTypeDTO deviceTypeDTO = getDeviceTypeDTO();
    ResponseEntity<DeviceTypeDTO> postyEntity =
        getRestTemplate()
            .postForEntity("/api/v1/sql/devicetype", deviceTypeDTO, DeviceTypeDTO.class);
    //    System.out.println(postyEntity);
    System.out.println("**DeviceType**");
    System.out.println(postyEntity.getBody());
    DeviceTypeDTO deviceType = postyEntity.getBody();
    assertThat(deviceType.getId()).isNotBlank();

    // Now create device
    Map<String, String> deviceAttributes = new HashMap<>();
    deviceAttributes.put("Msisdn", "12345");
    DeviceDTO device =
        new DeviceDTO(
            null, "Test Device", new Date(), new Date(), null, deviceType, null, deviceAttributes);

    System.out.println("Request to create device");
    System.out.println(device);
    ResponseEntity<DeviceDTO> devicePostResponse =
        getRestTemplate().postForEntity("/api/v1/sql/device", device, DeviceDTO.class);

    DeviceDTO deviceResponse = devicePostResponse.getBody();
    //    System.out.println(deviceResponse);
    assertThat(devicePostResponse.getBody().getId()).isNotBlank();

    // Now fetch resources and update value of one
    DeviceResourceDTO deviceResourceDTO =
        deviceResponse
            .getDeviceResourceModelList()
            .stream()
            .filter(r -> r.getSmartObject() == 3 && r.getInstanceId() == 0 && r.getResource() == 15)
            .findAny()
            .get();

    ResourceValue value = new ResourceValue();
    value.setStrValue("Update Test Resource");
    deviceResourceDTO.setValue(value);

    //    DeviceResourceDTO updatedResourceDTO = getRestTemplate()
    //        .patchForObject("/api/v1/sql/resource/"+deviceResourceDTO.getId(), deviceResourceDTO,
    // DeviceResourceDTO.class);
    //    System.out.println(updatedResourceDTO);

    //    String resourceUrl = "/api/v1/sql/resource/"+deviceResourceDTO.getId();
    //    ResponseEntity<DeviceResourceDTO> response = getRestTemplate()
    //        .exchange(resourceUrl, HttpMethod.PATCH, getPostRequestHeaders(deviceResourceDTO),
    //            DeviceResourceDTO.class);

    String resourceUrl = "/api/v1/sql/resource/" + deviceResourceDTO.getId() + "/value";
    getRestTemplate().put(resourceUrl, value);

    ResponseEntity<DeviceResourceDTO> updatedResource =
        getRestTemplate()
            .getForEntity(
                "/api/v1/sql/resource/" + deviceResourceDTO.getId(), DeviceResourceDTO.class);

    System.out.println(updatedResource);
    assertThat(updatedResource.getBody().getValue().getStrValue())
        .isEqualToIgnoringCase("Update Test Resource");
  }

  // Moved Over from TestHelper for Blog Post
  public HttpEntity getPostRequestHeaders(DeviceResourceDTO jsonPostBody) {
    List<MediaType> acceptTypes = new ArrayList<MediaType>();
    acceptTypes.add(RestMediaType.APPLICATION_PATCH_JSON);

    HttpHeaders reqHeaders = new HttpHeaders();
    reqHeaders.setContentType(RestMediaType.APPLICATION_PATCH_JSON);
    //    reqHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
    reqHeaders.setAccept(acceptTypes);

    return new HttpEntity<DeviceResourceDTO>(jsonPostBody, reqHeaders);
  }

  private DeviceTypeDTO getDeviceTypeDTO() {
    List<ObjectDTO> supportedObjects = getObjects();
    List<Protocol> supportedProtocols = new ArrayList<>();
    supportedProtocols.add(Protocol.COAP);
    supportedProtocols.add(Protocol.LWM2M);
    Map<String, String> attributes = new HashMap<>();
    return new DeviceTypeDTO(
        null,
        "TestType",
        "Test Manufacturer",
        "Test Model",
        supportedObjects,
        supportedProtocols,
        attributes);
  }

  private List<ObjectDTO> getObjects() {
    List<ObjectDTO> objects = new ArrayList<>();
    objects.add(new ObjectDTO(3));
    objects.add(new ObjectDTO(2048));
    return objects;
  }
}
