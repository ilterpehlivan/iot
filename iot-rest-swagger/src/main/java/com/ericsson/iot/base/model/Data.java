package com.ericsson.iot.base.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ApiModel(description = "Data model representation")
public class Data {
    Date timeStamp;
    String v;
    Type type;

    @ApiModelProperty(value = "Date value sent by resource",required = true)
    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @ApiModelProperty(value = "Value of the resource", required = true)
    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    @ApiModelProperty(value = "Type of the value.Default is string")
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }


    public static enum Type
    {
        INTEGER("integer"),  DOUBLE("double"),  STRING("string"), BOOLEAN("boolean"), BINARY("binary");

        private final String value;
        private static final Map<String, Type> CONSTANTS;

        static
        {
            CONSTANTS = new HashMap();
            Type[] arrayOfType;
            int j = (arrayOfType = values()).length;
            for (int i = 0; i < j; i++)
            {
                Type c = arrayOfType[i];
                CONSTANTS.put(c.value, c);
            }
        }

        private Type(String value)
        {
            this.value = value;
        }

        public String toString()
        {
            return this.value;
        }

        public static Type fromValue(String value)
        {
            Type constant = (Type)CONSTANTS.get(value);
            if (constant == null) {
                throw new IllegalArgumentException(value);
            }
            return constant;
        }
    }
}
