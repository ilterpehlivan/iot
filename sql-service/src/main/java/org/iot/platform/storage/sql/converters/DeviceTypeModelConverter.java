/**
 * Obsolete
 */

//package org.iot.platform.storage.sql.converters;
//
//import java.util.List;
//import java.util.stream.Collectors;
//import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
//import org.iot.platform.storage.sql.domain.DeviceType;
//import org.iot.platform.storage.sql.services.SmartObjectService;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class DeviceTypeModelConverter extends BaseModelConverter<DeviceType, DeviceTypeDTO> {
//
//  @Autowired SmartObjectService smartObjectService;
//
//  static BaseModelConverter<DeviceType, DeviceTypeDTO> instance = new DeviceTypeModelConverter();
//
//  @Override
//  public DeviceType convert2Entity(DeviceTypeDTO model) {
//    DeviceType type = new DeviceType();
//    type.setName(model.getName());
//    type.setModel(model.getModel());
//    type.setManufacturer(model.getManufacturer());
//    type.setAttributes(model.getAttributes());
//    type.setSupportedprotocols(model.getSupportedProtocols());
//    type.setSmartobjects(
//        model
//            .getSupportedObjects()
//            .stream()
//            .map(
//                o -> {
//                  return o.convert2EntityObject();
//                })
//            .collect(Collectors.toList()));
//
//    return type;
//  }
//
//  @Override
//  public List<DeviceType> convert2Entity(List<DeviceTypeDTO> model) {
//    return null;
//  }
//
//  @Override
//  public List<DeviceTypeDTO> convert2RestModel(List<DeviceType> entities) {
//    return entities
//        .stream()
//        .map(
//            t -> {
//              return convert2RestModel(t);
//            })
//        .collect(Collectors.toList());
//  }
//
//  @Override
//  public DeviceTypeDTO convert2RestModel(DeviceType entity) {
//    DeviceTypeDTO restModel =
//        new DeviceTypeDTO(
//            entity.getId().toString(),
//            entity.getName(),
//            entity.getManufacturer(),
//            entity.getModel(),
//            Util.convertEntity2RestModel(entity.getSmartobjects()),
//            entity.getSupportedprotocols(),
//            entity.getAttributes());
//    return restModel;
//  }
//
//  public static BaseModelConverter getInstance() {
//    return instance;
//  }
//}
