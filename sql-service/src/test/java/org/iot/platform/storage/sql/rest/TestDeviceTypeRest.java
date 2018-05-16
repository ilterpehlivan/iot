package org.iot.platform.storage.sql.rest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.iot.platform.storage.sql.controllers.DeviceTypeController;
import org.iot.platform.storage.sql.converters.BaseMapper;
import org.iot.platform.storage.sql.converters.BaseMapperImpl;
import org.iot.platform.storage.sql.converters.DeviceTypeMapper;
import org.iot.platform.storage.sql.converters.DeviceTypeMapperImpl;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.domain.OmaResource;
import org.iot.platform.storage.sql.domain.Protocol;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.services.DeviceTypeService;
import org.iot.platform.storage.sql.services.ResourceService;
import org.iot.platform.storage.sql.services.SmartObjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(value = DeviceTypeController.class, secure = false)
public class TestDeviceTypeRest {

  @Autowired private MockMvc mockMvc;

  @MockBean DeviceTypeService typeService;

  @MockBean SmartObjectService smartObjectService;

  @MockBean ResourceService resourceService;

  @TestConfiguration
  static class TestDeviceTestContextConfiguration {

    //    @Bean
    //    public SmartObjectService smartObjectService() {
    //      return new SmartObjectServiceImpl();
    //    }
    //
    //    @Bean
    //    public ResourceService resourceService() {
    //      return new ResourceServiceImpl();
    //    }

    @Bean
    public DeviceTypeMapper typeMapper() {
      return new DeviceTypeMapperImpl();
    }

    @Bean
    public BaseMapper baseMapper() {
      return new BaseMapperImpl();
    }
  }

  @Test
  public void testGetTypes() throws Exception {
    List<DeviceType> types = new ArrayList<>();
    DeviceType deviceType = new DeviceType();
    deviceType.setName("TestType");
    deviceType.setManufacturer("TEST MANUFACTURER");
    deviceType.addProtocol(Protocol.COAP);
    deviceType.setId(UUID.randomUUID());
    deviceType.getAttributes().put("Test", "test");
    deviceType.getSmartobjects().add(getSmartObject());
    types.add(deviceType);

    given(this.typeService.listAll()).willReturn(types);

    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/api/v1/sql/devicetype").accept(MediaType.APPLICATION_JSON);

    MvcResult result =
        mockMvc
            .perform(requestBuilder)
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(1)))
            .andExpect(jsonPath("$[0].id").exists())
            .andExpect(jsonPath("$[0].name",is("TestType")))
            .andReturn();

    System.out.println("Result:" + result.getResponse().getContentAsString());

    //    String expected =
    //        "[{id:\"59d9a546-efbe-4d7a-ad70-399696993041\",name:\"TestType\",manufacturer:\"TEST
    // MANUFACTURER\",supportedObjects:[{id:3,name:\"OMA device object\",description:\"Device
    // object\",multiple:false,mandatory:false}],supportedProtocols:[\"COAP\"],attributes:{Test:\"test\"}}]";

    //    JSONAssert.assertNotEquals(
    //        expected, result.getResponse().getContentAsString(), JSONCompareMode.LENIENT);
  }

  public SmartObject getSmartObject() {
    SmartObject deviceOmaObject = new SmartObject();
    deviceOmaObject.setId(3);
    deviceOmaObject.setDescription("Device object");
    deviceOmaObject.setMultiple(false);
    deviceOmaObject.setName("OMA device object");

    return deviceOmaObject;
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
