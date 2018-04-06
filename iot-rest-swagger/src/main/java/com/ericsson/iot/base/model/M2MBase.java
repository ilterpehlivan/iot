package com.ericsson.iot.base.model;

import java.util.HashMap;
import java.util.Map;

public class M2MBase {
	/* HL,Data*/
	String m2mdmInterface;
	
	/* querying parameters towards m2mdm*/
	Map<String,String> filters;
	
	/*Additional Header set for the request*/
	Map<String,String> headers;
	
	/*OperatorName*/
	String operator;
	
	/*String application name*/
	String application;
	
	/*UserName from application*/
	String requesterId;
	
	/*Filtering the query based on resources*/
	Map<String,String> resourceFilter;
	
	boolean isAdditionalData;
	boolean isLatestData;
	
	
	/* Initialize the Maps */
	public M2MBase() {
		resourceFilter = new HashMap<String, String>();
		filters = new HashMap<String,String>();
		headers = new HashMap<String,String>();
	}
	public String getM2mdmInterface() {
		return m2mdmInterface;
	}
	public void setM2mdmInterface(String m2mdmInterface) {
		this.m2mdmInterface = m2mdmInterface;
	}
	public Map<String, String> getFilters() {
		return filters;
	}
	public void setFilters(Map<String, String> filters) {
		this.filters = filters;
	}
	public Map<String, String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String, String> headers) {
		this.headers = headers;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(String requesterId) {
		this.requesterId = requesterId;
	}
	public Map<String, String> getResourceFilter() {
		return resourceFilter;
	}
	public void setResourceFilter(Map<String, String> resourceFilter) {
		this.resourceFilter = resourceFilter;
	}
	public boolean isAdditionalData() {
		return isAdditionalData;
	}
	public void setAdditionalData(boolean isAdditionalData) {
		this.isAdditionalData = isAdditionalData;
	}
	public boolean isLatestData() {
		return isLatestData;
	}
	public void setLatestData(boolean isLatestData) {
		this.isLatestData = isLatestData;
	}
	
	

	
}
