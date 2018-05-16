/**
 * Obsolete instead using MapStruct
 */

//package org.iot.platform.storage.sql.converters;
//
//import java.util.List;
//import org.iot.platform.storage.sql.domain.Device;
//import org.iot.platform.storage.sql.domain.DeviceType;
//
//// T is the JPA and E is Rest dto
//public abstract class BaseModelConverter<T, E> {
//
//  public static BaseModelConverter getConverter(Class cls) throws CloneNotSupportedException {
//    if (DeviceType.class.isAssignableFrom(cls)) {
//      return DeviceTypeModelConverter.getInstance();
//    } else if (Device.class.isAssignableFrom(cls)){
//      return DeviceModelConverter.getInstance();
//    }
//
//    throw new CloneNotSupportedException("Not supported class");
//  }
//
//  public abstract T convert2Entity(E model);
//
//  public abstract List<T> convert2Entity(List<E> model);
//
//  public abstract List<E> convert2RestModel(List<T> entities);
//
//  public abstract E convert2RestModel(T entity);
//
//}
