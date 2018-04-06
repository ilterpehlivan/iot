package com.ericsson.iot.rest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Component
public class EventGenerator {
	
//	Random random = new Random();
	org.apache.camel.impl.ActiveMQUuidGenerator uuidGenerator = new org.apache.camel.impl.ActiveMQUuidGenerator();
	
	public String generateEvent(){
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode event = mapper.createObjectNode();
		event.put("id", uuidGenerator.generateUuid());
		event.put("message", "Event Happened");
		/*StringBuilder sb = new StringBuilder("{");
		sb.append("id:").append(uuidGenerator.generateUuid());
		sb.append("name:").append("Event Happened").append("}");
		*/
		try {
			return mapper.writeValueAsString(event);
		} catch (JsonProcessingException e) {
			return "NO EVENT GENERATED";
		}
	}
}
