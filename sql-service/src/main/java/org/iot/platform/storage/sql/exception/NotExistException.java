package org.iot.platform.storage.sql.exception;

import java.util.UUID;

public class NotExistException extends BaseException {

  public NotExistException(String s, UUID id) {
    super(s,id);
  }
}
