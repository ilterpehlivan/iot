package com.ericsson.iot.rest.beans;

import com.ericsson.iot.base.model.Data;
import com.ericsson.iot.base.model.LwM2MJson;
import com.ericsson.iot.base.model.Resource;
import com.ericsson.iot.base.model.Sensor;
import com.ericsson.iot.rest.DeviceDataResponse;
import com.ericsson.iot.rest.ResourceList;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class ResponseGenerator {

    public DeviceDataResponse getDeviceData(String id) {
        DeviceDataResponse response = new DeviceDataResponse();
        List<Sensor> sensors = new ArrayList<>();
        Sensor sensor = new Sensor();
        sensor.setId("test");
        List<Resource> resources = new ArrayList<>();
        Resource resource = new Resource();
        List<Data> dataList = new ArrayList<>();

        Data data1 = new Data();
        data1.setTimeStamp(new Date());

        Data.Type type = Data.Type.fromValue("boolean");
        data1.setType(type);
        data1.setV(String.valueOf(true));


        Data data2 = new Data();
        data2.setTimeStamp(new Date());

        Data.Type type2 = Data.Type.fromValue("double");
        data1.setType(type2);
        data1.setV(String.valueOf(23.5d));

        dataList.add(data1);
        dataList.add(data2);

        resources.add(resource);
        sensors.add(sensor);

        resource.setData(dataList);
        sensor.setResources(resources);
        response.setSensors(sensors);
        return new DeviceDataResponse();
    }

    public void getDummyStringValue(Exchange exchange) {
        exchange.getIn().setBody(null);
        exchange.getIn().setHeaders(null);
        exchange.getOut().setBody("OK");
        exchange.getOut().setHeader(Exchange.HTTP_RESPONSE_CODE, "204");
    }


    public ResourceList getDummyResourceList() {
        return null;
    }

    public Resource getDummyResource() {
        return null;
    }

    public LwM2MJson[] getDummyLwM2MJson() {
        return (LwM2MJson[]) new ArrayList<LwM2MJson>().toArray();
    }



}
