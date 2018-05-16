/**
 * Obsolete instead using MapStruct
 */
//package org.iot.platform.storage.sql.converters;
//
//import java.util.List;
//import org.iot.platform.storage.sql.controllers.dto.DeviceDTO;
//import org.iot.platform.storage.sql.domain.Device;
//
//public class DeviceModelConverter extends BaseModelConverter<Device,DeviceDTO> {
//
//  static DeviceModelConverter instance = new DeviceModelConverter();
//
//  @Override
//  public Device convert2Entity(DeviceDTO model) {
//    return null;
//  }
//
//  @Override
//  public List<Device> convert2Entity(List<DeviceDTO> model) {
//    return null;
//  }
//
//  @Override
//  public List<DeviceDTO> convert2RestModel(List<Device> entities) {
//    return null;
//  }
//
//  @Override
//  public DeviceDTO convert2RestModel(Device entity) {
//    return null;
//  }
//
//  public static DeviceModelConverter getInstance(){
//    return instance;
//  }
//}
