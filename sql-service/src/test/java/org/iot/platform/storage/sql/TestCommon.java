package org.iot.platform.storage.sql;

import java.nio.ByteBuffer;
import org.junit.Test;
import org.eclipse.leshan.core.model.ResourceModel.Type;

public class TestCommon {

  @Test
  public void testObjectConvertion() {
    Object test1 = new Integer(2);
    Object test2 = new String("Test2");
    Object test3 = new Boolean(true);

    byte [] value1 = convert2ByteArray(test1);
    byte [] value2 = convert2ByteArray(test2);
    byte [] value3 = convert2ByteArray(test3);

    System.out.println("Values->"+value1+value2+value3);

//    int convertedIntValue = fromByteArray2Int(value1);
    System.out.println(getValue(value1,Type.INTEGER));

    String convertedStrValue = new String(value2);
    System.out.println(convertedStrValue);

    Boolean convertedBolValue = fromByteArray2Int(value3) == 0 ? false:true;
    System.out.println(convertedBolValue);

    Integer testInt = new Integer(0);
    System.out.println(testInt);
  }


  int fromByteArray2Int(byte[] bytes) {
    return ByteBuffer.wrap(bytes).getInt();
  }

  byte[] int2ByteArray(int value) {
    return  ByteBuffer.allocate(4).putInt(value).array();
  }

  private byte[] convert2ByteArray(Object test1) {

    if(test1 instanceof Integer){
      return int2ByteArray((Integer)test1);
    }
    if(test1 instanceof String){
      return ((String) test1).getBytes();
    }

    if(test1 instanceof Boolean){
      int value = ((Boolean)test1 ? 1: 0);
      return int2ByteArray(value);
    }

    //TODO: add other types too
    return null;
  }

  private Object getValue(byte[] bytValue, Type type){
    if(type.equals(Type.STRING)){
      return new String(bytValue);
    }
    if(type.equals(Type.BOOLEAN)){
      Boolean convertedBolValue = fromByteArray2Int(bytValue) == 0 ? false:true;
      return convertedBolValue;
    }
    if(type.equals(Type.INTEGER)){
      return fromByteArray2Int(bytValue);
    }

    return null;
  }
}
