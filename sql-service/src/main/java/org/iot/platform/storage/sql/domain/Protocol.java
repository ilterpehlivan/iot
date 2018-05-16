package org.iot.platform.storage.sql.domain;

import java.util.ArrayList;
import java.util.List;

public enum Protocol {
  COAP,
  LWM2M,
  MQTT,
  HTTP;

  public static List<Protocol> convert2ProtocolList(List<String> supportedProtocols) {
    List<Protocol> protocols = new ArrayList<>();
    supportedProtocols.forEach(s->protocols.add(Protocol.valueOf(s)));
    return protocols;
  }
}
