package org.iot.platform.storage.sql.controllers.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.eclipse.leshan.core.model.ResourceModel.Type;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResourceValue {

  String strValue;
  Integer intValue;
  Boolean boolValue;
  Float fltValue;
  Byte[] bytValue;

  public Float getFltValue() {
    return fltValue;
  }

  public void setFltValue(Float fltValue) {
    this.fltValue = fltValue;
  }

  public String getStrValue() {
    return strValue;
  }

  public void setStrValue(String strValue) {
    this.strValue = strValue;
  }

  public Integer getIntValue() {
    return intValue;
  }

  public void setIntValue(Integer intValue) {
    this.intValue = intValue;
  }

  public Boolean getBoolValue() {
    return boolValue;
  }

  public void setBoolValue(Boolean boolValue) {
    this.boolValue = boolValue;
  }

  public Byte[] getBytValue() {
    return bytValue;
  }

  public void setBytValue(Byte[] bytValue) {
    this.bytValue = bytValue;
  }

  public ResourceValue getValue(byte[] bytValue, Type type) {

    if (bytValue == null) return null;

    if (type.equals(Type.STRING)) {
      String str = new String(bytValue);
      setStrValue(str);
    } else if (type.equals(Type.BOOLEAN)) {
      Boolean convertedBolValue = fromByteArray2Int(bytValue) == 0 ? false : true;
      setBoolValue(convertedBolValue);
    } else if (type.equals(Type.INTEGER)) {
      setIntValue(fromByteArray2Int(bytValue));
    } else if (type.equals(Type.FLOAT)) {
      setFltValue(byte2Float(bytValue));
    }
    return this;
  }

  /**
   * This method converts actual resource value into byte array
   * depending on the setted value
   * @return
   */
  public byte[] convert2ByteArray(){
    if(intValue != null){
      return int2ByteArray(intValue);
    }
    if(strValue != null) {
      return strValue.getBytes();
    }
    if (fltValue != null) {
      return float2ByteArray(fltValue);
    }
    if(boolValue != null){
      int value = getBoolValue() ? 1 : 0;
      return int2ByteArray(value);
    }

    return null;
  }

  public static int fromByteArray2Int(byte[] bytes) {
    return ByteBuffer.wrap(bytes).getInt();
  }

  public static byte[] int2ByteArray(int value) {
    return ByteBuffer.allocate(4).putInt(value).array();
  }

  public static byte[] float2ByteArray(float value) {
    return ByteBuffer.allocate(4).putFloat(value).array();
  }

  public static float byte2Float(byte[] bytes) {
    return ByteBuffer.wrap(bytes).getFloat();
  }

  public byte[] convert2ByteArray(Type type) {
    if (type.equals(Type.STRING)) {
      return this.getStrValue().getBytes();
    } else if (type.equals(Type.BOOLEAN)) {
      int value = getBoolValue() ? 1 : 0;
      return int2ByteArray(value);
    } else if (type.equals(Type.INTEGER)) {
      int2ByteArray(getIntValue());
    } else if (type.equals(Type.FLOAT)) {
      float2ByteArray(getFltValue());
    }

    return null;
  }


  @Override
  public String toString() {
    return "ResourceValue{" +
        "strValue='" + strValue + '\'' +
        ", intValue=" + intValue +
        ", boolValue=" + boolValue +
        ", fltValue=" + fltValue +
        ", bytValue=" + Arrays.toString(bytValue) +
        '}';
  }
}
