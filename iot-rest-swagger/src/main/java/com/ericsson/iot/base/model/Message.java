package com.ericsson.iot.base.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Message implements Serializable {
	String id;
	/* Client ID */
	String from;
	String timeStamp;
//	List<Device> devices;
	Map<String, String> extraMeta;

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

//	public List<? extends Device> getDevices() {
//		return devices;
//	}

//	public void setDevices(List<? extends Device> devices) {
//		this.devices = (List<Device>) devices;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Map<String, String> getExtraMeta() {
		return extraMeta;
	}

	public void setExtraMeta(Map<String, String> extraMeta) {
		this.extraMeta = extraMeta;
	}

}
