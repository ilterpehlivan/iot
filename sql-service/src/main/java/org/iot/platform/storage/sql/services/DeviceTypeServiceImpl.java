package org.iot.platform.storage.sql.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import org.iot.platform.storage.sql.controllers.dto.DeviceTypeDTO;
import org.iot.platform.storage.sql.converters.Util;
import org.iot.platform.storage.sql.domain.DeviceType;
import org.iot.platform.storage.sql.domain.SmartObject;
import org.iot.platform.storage.sql.exception.NotExistException;
import org.iot.platform.storage.sql.repositories.DeviceTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeviceTypeServiceImpl implements DeviceTypeService {

  @Autowired DeviceTypeRepository repository;
  @Autowired SmartObjectService smartObjectService;

  @Override
  public List<DeviceType> listAll() {
    List<DeviceType> deviceTypes = new ArrayList<>();
    repository.findAll().forEach(t -> deviceTypes.add(t));
    return deviceTypes;
  }

  @Override
  public DeviceType findDeviceType(UUID id) {
    return repository.findById(id).get();
  }

  @Override
  public DeviceType createDeviceType(DeviceType type) {
    // first we need to fetch current smart objects from DB
    List<SmartObject> smartObjects =
        type.getSmartobjects()
            .stream()
            .map(
                o -> {
                  SmartObject temp = smartObjectService.getById(o.getId());
                  return temp;
                })
            .collect(Collectors.toList());
    type.setSmartobjects(smartObjects);
    return repository.save(type);
  }

  @Override
  public DeviceType createDeviceType(DeviceTypeDTO restType) {
    return repository.save(convert2Entity(restType));
  }

  private DeviceType convert2Entity(DeviceTypeDTO restType) {
    DeviceType type = getDeviceType(restType);
    return repository.save(type);
  }

  private DeviceType getDeviceType(DeviceTypeDTO restType) {
    // TODO: add validator for the rest dto
    DeviceType type = new DeviceType();
    type.setManufacturer(restType.getManufacturer());
    type.setAttributes(restType.getAttributes());
    type.setModel(restType.getModel());
    type.setName(restType.getName());
    type.setSupportedprotocols(restType.getSupportedProtocols());
    // smartObjects
    type.setSmartobjects(
        restType
            .getSupportedObjects()
            .stream()
            .map(
                o -> {
                  return o.convert2EntityObject();
                })
            .collect(Collectors.toList()));
    return type;
  }

  @Override
  public DeviceType updateDeviceType(UUID id, DeviceType type) throws NotExistException {
    Optional<DeviceType> currentType = repository.findById(id);
    if (!currentType.isPresent())
      throw new NotExistException("Device type with Id {} does not exist", id);
    return repository.save(type);
  }

  @Override
  public DeviceType updateDeviceType(UUID id, DeviceTypeDTO restType) throws NotExistException {
    DeviceType updateType = getDeviceType(restType);
    Optional<DeviceType> existingType = repository.findById(id);
    if (!existingType.isPresent())
      throw new NotExistException("Device type with Id {} does not exist", id);
    // Copy to existing type
    Util.copyBeanProperties(updateType, existingType.get());
    return repository.save(existingType.get());
  }

  @Override
  public List<SmartObject> getSmartObjectsOfType(UUID typeId) throws NotExistException {
    Optional<DeviceType> type = repository.findById(typeId);
    if (!type.isPresent())
      throw new NotExistException("Device type with Id {} does not exist", typeId);
    return type.get().getSmartobjects();
  }

  @Override
  public void removeDeviceType(UUID id) throws NotExistException {
    Optional<DeviceType> type = repository.findById(id);
    if (!type.isPresent()) throw new NotExistException("Device type with Id {} does not exist", id);

    repository.delete(type.get());
  }

  @Override
  public DeviceType findDeviceType(String name) {
    return repository.findDeviceTypeWithName(name);
  }
}
