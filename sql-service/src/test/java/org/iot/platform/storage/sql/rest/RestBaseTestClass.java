package org.iot.platform.storage.sql.rest;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestBaseTestClass {
  @Autowired
  private TestRestTemplate restTemplate;

  public TestRestTemplate getRestTemplate() {
    return restTemplate;
  }

  public void setRestTemplate(TestRestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }


}
