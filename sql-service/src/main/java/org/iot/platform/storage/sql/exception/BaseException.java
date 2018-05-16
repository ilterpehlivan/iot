package org.iot.platform.storage.sql.exception;

import org.apache.logging.log4j.message.ParameterizedMessage;

public class BaseException extends Exception {

  public BaseException() {}

  public BaseException(String message, Object... params) {
    super(ParameterizedMessage.format(message, params));
  }
}
