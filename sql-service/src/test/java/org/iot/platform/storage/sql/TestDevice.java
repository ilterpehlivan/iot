package org.iot.platform.storage.sql;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.controllers.dto.ObjectDTO;
import org.iot.platform.storage.sql.controllers.dto.ResourceValue;
import org.iot.platform.storage.sql.converters.BaseMapper;
import org.iot.platform.storage.sql.converters.BaseMapperImpl;
import org.iot.platform.storage.sql.converters.DeviceTypeMapper;
import org.iot.platform.storage.sql.converters.DeviceTypeMapperImpl;
import org.iot.platform.storage.sql.domain.Device;
import org.iot.platform.storage.sql.domain.Device.Status;
import org.iot.platform.storage.sql.domain.DeviceResource;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.Protocol;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.repositories.DeviceRepository;
import org.iot.platform.storage.sql.repositories.DeviceResourceRepository;
import org.iot.platform.storage.sql.repositories.DeviceTypeRepository;
import org.iot.platform.storage.sql.repositories.ResourceRepository;
import org.iot.platform.storage.sql.repositories.SmartObjectRepository;
import org.iot.platform.storage.sql.services.DeviceService;
import org.iot.platform.storage.sql.services.DeviceServiceImpl;
import org.iot.platform.storage.sql.services.DeviceTypeService;
import org.iot.platform.storage.sql.services.DeviceTypeServiceImpl;
import org.iot.platform.storage.sql.services.ResourceService;
import org.iot.platform.storage.sql.services.ResourceServiceImpl;
import org.iot.platform.storage.sql.services.SmartObjectService;
import org.iot.platform.storage.sql.services.SmartObjectServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import org.hamcrest.collection.IsEmptyCollection;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.collection.IsIterableContainingInAnyOrder.containsInAnyOrder;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestDevice {

  @TestConfiguration
  static class TestDeviceTestContextConfiguration {

    @Bean
    public SmartObjectService smartObjectService() {
      return new SmartObjectServiceImpl();
    }

    @Bean
    public DeviceTypeService deviceTypeService() {
      return new DeviceTypeServiceImpl();
    }

    @Bean
    public ResourceService resourceService() {
      return new ResourceServiceImpl();
    }

    @Bean
    public DeviceTypeMapper typeMapper() {
      return new DeviceTypeMapperImpl();
    }

    @Bean
    public BaseMapper baseMapper() {
      return new BaseMapperImpl();
    }

    @Bean
    public DeviceService deviceService() {
      return new DeviceServiceImpl();
    }
  }

  @Autowired private TestEntityManager entityManager;

  @Autowired private DeviceRepository deviceRepository;

  @Autowired private DeviceTypeRepository deviceTypeRepository;

  @Autowired SmartObjectRepository smartObjectRepository;
  @Autowired ResourceRepository resourceRepository;

  @Autowired DeviceResourceRepository deviceResourceRepository;

  @Autowired SmartObjectService smartObjectService;

  @Autowired DeviceService deviceService;

  List<SmartObject> smartObjects;

  @Before
  public void loadSmartObjects() {
    smartObjects = (List<SmartObject>) smartObjectRepository.findAll();
  }

  @Test
  public void testDeviceCreate() {
    Device device = new Device();
    device.setName("test1");
    device.setProfileCreationTime(LocalDateTime.now());
    device.setStatus(Device.Status.CREATED);
    DeviceType deviceType = new DeviceType();
    //      deviceType.setSmartobjects(smartObjects);
    deviceType.setManufacturer("TEST MANUFACTURER");
    deviceType.addProtocol(Protocol.COAP);
    device.setType(deviceType);

    deviceRepository.save(device);
    Device persDevice = deviceRepository.findById(device.getId()).get();
    assertThat(device.getName()).isEqualTo("test1");
    assertThat(device.getId()).isNotNull();
    assertThat(device.getType().getSupportedprotocols(), hasItem(Protocol.COAP));
  }

  @Test
  public void testMultipleDeviceTypeWithSameSmartObject() {
    DeviceType deviceType = new DeviceType();
    deviceType.setSmartobjects(smartObjects);
    deviceType.setManufacturer("TEST MANUFACTURER-1");
    deviceType.addProtocol(Protocol.MQTT);

    DeviceType deviceType2 = new DeviceType();
    deviceType2.setSmartobjects(smartObjects);
    deviceType2.setManufacturer("TEST MANUFACTURER-2");
    deviceType2.addProtocol(Protocol.HTTP);

    entityManager.persist(deviceType);
    entityManager.persist(deviceType2);

    DeviceType type1 = deviceTypeRepository.findById(deviceType.getId()).get();
    assertThat(type1.getSmartobjects()).isNotNull();
    System.out.println("SmartObjects for Type1->");
    type1.getSmartobjects().forEach(s -> System.out.println(s.getId()));
    assertThat(type1.getManufacturer()).isEqualToIgnoringCase("TEST MANUFACTURER-1");

    DeviceType type2 = deviceTypeRepository.findById(deviceType2.getId()).get();
    assertThat(type2.getSmartobjects()).isNotNull();
    assertThat(type2.getManufacturer()).isEqualToIgnoringCase("TEST MANUFACTURER-2");
    System.out.println("SmartObjects for Type2->");
    type2.getSmartobjects().forEach(s -> System.out.println(s.getId()));
  }

  @Test
  public void testDeviceResource() {

    // Create device Type
    DeviceType deviceType = new DeviceType();
    deviceType.setSmartobjects(smartObjects);
    deviceType.setManufacturer("TEST MANUFACTURER-1");
    deviceType.addProtocol(Protocol.MQTT);

    DeviceResource resource1 = new DeviceResource();
    resource1.setCreationTime(LocalDateTime.now());
    resource1.setInstanceId(0);
    OmaResource omaResource = getResource(3, 0);
    resource1.setResource(omaResource);

    Device device = new Device();
    device.setName("TestDevice1");
    device.addResource(resource1);
    device.setType(deviceType);

    resource1.setDevice(device);

    Device savedDevice = deviceRepository.save(device);
    entityManager.flush();
    assertThat(savedDevice.getResources()).isNotNull();
    //    assertThat(savedDevice.getResources().get(0).getReferenceobject().getId()).isEqualTo(3);

    // Test cascade
    DeviceResource savedResource = deviceResourceRepository.findById(resource1.getId()).get();
    assertThat(savedResource).isNotNull();

    // remove resource
    deviceResourceRepository.delete(savedResource);
    //    assertThat(savedDevice.getResources()).isNull();

  }

  @Test
  public void testDeviceHierarchy() {
    // First Device Type
    DeviceType deviceType = new DeviceType();
    deviceType.setSmartobjects(smartObjects);
    deviceType.setManufacturer("TEST MANUFACTURER-1");
    deviceType.addProtocol(Protocol.MQTT);

    // Add smart object to type
    deviceType.setSmartobjects(smartObjects);
    // save type
    deviceTypeRepository.save(deviceType);
    entityManager.flush();

    // create device
    Device device = new Device();
    device.setName("Test Device");
    device.setType(deviceType);
    device.setStatus(Status.CREATED);

    // Create device resources
    DeviceResource resource1 = new DeviceResource();
    resource1.setCreationTime(LocalDateTime.now());
    resource1.setInstanceId(0);
    resource1.setResource(getResource(3, 0)); // device resource 0
    resource1.setSmartobject(smartObjects.stream().filter(s -> s.getId() == 3).findAny().get());

    // resource2
    DeviceResource resource2 = new DeviceResource();
    resource2.setCreationTime(LocalDateTime.now());
    resource2.setInstanceId(0);
    resource2.setResource(getResource(3, 1)); // device resource 1
    resource2.setBytValue(ResourceValue.int2ByteArray(30));
    resource2.setSmartobject(smartObjects.stream().filter(s -> s.getId() == 3).findAny().get());

    // Set the parent entity
    resource1.setDevice(device);
    resource2.setDevice(device);

    // Object 0
    List<DeviceResource> resources = new ArrayList<>();

    resources.add(resource1);
    resources.add(resource2);
    device.setResources(resources);

    //    System.out.println("**PRINT**");
    //    resources.forEach(r -> System.out.println(r.getId() +"-"+ r.getResource().getResourceId()
    // + "-" + r.getInstanceId()));
    // save device
    deviceRepository.save(device);
    entityManager.flush();

    // Assert
    Device savedDevice = deviceRepository.findById(device.getId()).get();
    assertThat(savedDevice).isNotNull();
    assertThat(savedDevice.getResources(), hasSize(2));
    assertThat(savedDevice.getResources(), hasItem(resource1));
    assertThat(savedDevice.getResources(), hasItem(resource2));
  }

  @Test
  public void testDeviceHierarchyWithAutoResourceGeneration() {
    // First Device Type
    DeviceType deviceType = new DeviceType();
    deviceType.setSmartobjects(getObjects(2048));
    deviceType.setManufacturer("TEST MANUFACTURER-1");
    deviceType.addProtocol(Protocol.MQTT);

    // Add smart object to type
    deviceType.setSmartobjects(smartObjects);
    // save type
    deviceTypeRepository.save(deviceType);
    entityManager.flush();

    // create device
    Device device = new Device();
    device.setName("Test Device");
    device.setType(deviceType);
    device.setStatus(Status.CREATED);

    System.out.println("Saving device->" + device);

    Device savedDevice = deviceService.saveOrUpdate(device, true);
    System.out.println("Ilter: Device is saved");
    entityManager.flush();
    System.out.println(savedDevice);
  }

  private List<SmartObject> getObjects(Integer...objectIds) {
    List<SmartObject> objects = new ArrayList<>();
    for (SmartObject o : smartObjects ) {
        for (int i =0; i< objectIds.length; i ++){
          if (objectIds[i] == o.getId()) {
            objects.add(o);
          }
        }
    }

    return objects;

  }


  @Autowired DeviceTypeMapper deviceTypeMapper;
  @Autowired DeviceTypeService deviceTypeService;

  @Test
  public void testDeviceTypeDTO() {
    List<ObjectDTO> objects = new ArrayList<>();
    objects.add(new ObjectDTO(3));
    objects.add(new ObjectDTO(2048));
    List<ObjectDTO> supportedObjects = objects;
    List<Protocol> supportedProtocols = new ArrayList<>();
    supportedProtocols.add(Protocol.COAP);
    supportedProtocols.add(Protocol.LWM2M);
    Map<String, String> attributes = new HashMap<>();
    DeviceTypeDTO deviceTypeDTO =
        new DeviceTypeDTO(
            null,
            "TestType",
            "Test Manufacturer",
            "Test Model",
            supportedObjects,
            supportedProtocols,
            attributes);

    DeviceType entity = deviceTypeMapper.toEntity(deviceTypeDTO);
    DeviceType savedType = deviceTypeService.createDeviceType(entity);

    entityManager.flush();

    //    System.out.println(savedType);
    assertThat(savedType.getSmartobjects().get(0).getName()).isNotBlank();
  }

  private OmaResource getResource(int objectId, int resourceId) {
    return resourceRepository.findResourceWithOmaIdAndResourceId(objectId, resourceId);
  }

  private List<OmaResource> getDeviceOmaResources() {
    List<OmaResource> deviceOmaResources = new ArrayList<>();
    OmaResource resource1 = new OmaResource();
    resource1.setResourceId(0);
    resource1.setName("Manufacturer");

    OmaResource resource2 = new OmaResource();
    resource2.setResourceId(1);
    resource2.setName("Model Number");

    deviceOmaResources.add(resource1);
    deviceOmaResources.add(resource2);

    return deviceOmaResources;
  }
}
