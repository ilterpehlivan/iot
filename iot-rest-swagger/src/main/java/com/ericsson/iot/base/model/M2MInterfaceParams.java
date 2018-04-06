package com.ericsson.iot.base.model;

public interface M2MInterfaceParams {
	public static final String DATA_INTERFACE="data";
	public static final String PROVISION_INTERFACE="hl";
	/*Data interface keys*/
	public static final String DATA_DEVICE="dgw";
	public static final String DATA_SENSOR="sen";
	public static final String DATA_DEVICE_SPEC="gatewaySpec";
	public static final String DATA_SENSOR_SPEC="sensorSpec";
	
	/*Provisioning interface keys*/
	public static final String PROV_DEVICE="deviceGateways";
	public static final String PROV_DEVICE_SPEC="deviceGatewaySpecifications";
	
	/*Additional Filter keys*/
	public static final String FILTER_TIME_START="t1";
	public static final String FILTER_TIME_END="t2";
	public static final String FILTER_SOURCE_IDENTIFY="sourceIdentifiers";
	
	/*SourceIdentify options*/
	public static final String SOURCE_IDENTIFY_ALL="ALL";
	
	public static final String ORDER_BY_DESC="DESC";
	public static final String ORDER_BY_ASC="ASC";

	
	/*static final Map<String, String> SOURCE_IDENTIFY_PARAMS = ImmutableMap.of(
		    "all", "ALL",
		    2, "two"
		);*/
	

}
