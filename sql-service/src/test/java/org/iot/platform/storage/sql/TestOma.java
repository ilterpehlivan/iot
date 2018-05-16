package org.iot.platform.storage.sql;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.PersistenceException;
import org.eclipse.leshan.core.model.ResourceModel.Operations;
import org.eclipse.leshan.core.model.ResourceModel.Type;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.repositories.ResourceRepository;
import org.iot.platform.storage.sql.repositories.SmartObjectRepository;
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

@RunWith(SpringRunner.class)
@DataJpaTest
public class TestOma {

  @TestConfiguration
  static class TestDeviceTestContextConfiguration {

    @Bean
    public SmartObjectService smartObjectService() {
      return new SmartObjectServiceImpl();
    }

    @Bean
    public ResourceService resourceService() {
      return new ResourceServiceImpl();
    }
  }

  @Autowired SmartObjectRepository smartObjectRepository;
  @Autowired ResourceRepository resourceRepository;

  List<SmartObject> smartObjects;

  @Before
  public void loadSmartObjects() {
    smartObjects = (List<SmartObject>) smartObjectRepository.findAll();
  }

  @Test
  public void testVerifyLoadedSmartObjects() {
    Optional<SmartObject> deviceObject = smartObjectRepository.findById(3);
    assertThat(deviceObject.get()).isNotNull();
    assertThat(deviceObject.get().getName()).isEqualToIgnoringCase("Device");

    //Check resources
    List<OmaResource> resourcesWithSmartObject = resourceService.findResourcesWithSmartObject(3);
    assertThat(resourcesWithSmartObject).isNotNull();
    assertThat(resourcesWithSmartObject.size()).isEqualTo(23);

  }

  @Test
  public void testMultipleSameResourceId() {
    List<OmaResource> resourcesWithOmaId = resourceRepository.findResourcesWithOmaId(0);
    assertThat(resourcesWithOmaId.size()).isNotEqualTo(0);
  }

  @Autowired private TestEntityManager entityManager;

  @Test
  public void testUniqueResource() {
    List<OmaResource> deviceOmaResources = getDeviceOmaResources();
    SmartObject deviceOmaObject = new SmartObject();
    deviceOmaObject.setId(3);
    deviceOmaObject.setDescription("Device object");
    deviceOmaObject.setMultiple(false);
    deviceOmaObject.setName("OMA device object");
    //    deviceOmaObject.setOmaResources(getDeviceOmaResources());

    deviceOmaResources.forEach(r -> r.setSmartobject(deviceOmaObject));

    // This should fail with Duplication

    try {
      resourceRepository.saveAll(deviceOmaResources);
      entityManager.flush();
      fail("No Error thrown which was expected");
    } catch (PersistenceException e) {
      assertThat(e.getLocalizedMessage()).contains("ConstraintViolationException");
    }
  }

  @Autowired SmartObjectService smartObjectService;
  @Autowired ResourceService resourceService;

  @Test
  public void testDuplicateSmartObjectAndResource() {
    // First verify the entities stored in DB
    List<SmartObject> smartObjects = smartObjectService.listAll();
    assertThat(smartObjects.size()).isEqualTo(3);
    SmartObject object1 = getObject(smartObjects, 3);
    assertThat(object1.getName()).isEqualToIgnoringCase("Device");

    List<OmaResource> omaResources = resourceService.listAll();
    assertThat(omaResources.size()).isEqualTo(37);
    OmaResource resource1 = getResource(omaResources, 3, 0);
    assertThat(resource1.getName()).isEqualToIgnoringCase("Manufacturer");

    omaResources.forEach(r->System.out.println("Resource->" + r.getResourceId() + "-"+ r.getSmartobject().getId()));

    // Now we create objects with same values
    SmartObject deviceOmaObject = new SmartObject();
    deviceOmaObject.setId(3);
    deviceOmaObject.setDescription("Device object");
    deviceOmaObject.setMultiple(false);
    deviceOmaObject.setName("OMA device object");
    List<OmaResource> deviceOmaResources = getDeviceOmaResources(deviceOmaObject);
    deviceOmaResources.forEach(r -> r.setSmartobject(deviceOmaObject));

    // now save duplicated object and resource
    smartObjectService.saveOrUpdate(deviceOmaObject);
    entityManager.flush();
    //    smartObjectService.saveAll(smartObjects);
    //    entityManager.flush();

    // Expectetation is that the object and resources will be updated instead of insert
//    List<SmartObject> smartObjects2 = smartObjectService.listAll();
//    assertThat(smartObjects2.size()).isEqualTo(3);
//    SmartObject object2 = getObject(smartObjects2, 3);
//    assertThat(object2.getName()).isEqualToIgnoringCase("OMA device object");

    List<OmaResource> omaResources2 = resourceService.listAll();
    omaResources2.forEach(r->System.out.println("Resource->" + r.getResourceId() + "-"+ r.getSmartobject().getId()));

    assertThat(omaResources2.size()).isEqualTo(37);
//    OmaResource resource2 = getResource(omaResources2, 3, 1);
//    assertThat(resource2.getName()).isEqualToIgnoringCase("Model Number");
  }

  private List<OmaResource> getDeviceOmaResources(SmartObject deviceOmaObject) {
    List<OmaResource> deviceOmaResources = new ArrayList<>();
    OmaResource resource1 = new OmaResource();
    resource1.setResourceId(0);
    resource1.setName("Manufacturer");
    resource1.setSmartobject(deviceOmaObject);

    OmaResource resource2 = new OmaResource();
    resource2.setResourceId(1);
    resource2.setName("Model Number");
    resource2.setSmartobject(deviceOmaObject);

    deviceOmaResources.add(resource1);
    deviceOmaResources.add(resource2);

    return deviceOmaResources;
  }

  private OmaResource getResource(List<OmaResource> omaResources, int objectId, int resourceId) {
    return omaResources
        .stream()
        .filter(r -> r.getSmartobject().getId() == objectId && r.getResourceId() == resourceId)
        .findAny()
        .get();
  }

  private SmartObject getObject(List<SmartObject> smartObjects, int objectId) {
    return smartObjects.stream().filter(o -> o.getId() == objectId).findAny().get();
  }

  @Test
  public void testEmptyObjectReference4Resource() {
    OmaResource resource1 =
        new OmaResource(
            2050, "Thermometer", Operations.RW, true, false, Type.STRING, "XX", "XX", "TEST");

    resourceRepository.save(resource1);
    entityManager.flush();
    assertThat(resource1.getSmartobject()).isNull();
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
