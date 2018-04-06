package com.ericsson.iot.rest;

import com.ericsson.iot.base.model.BaseResponse;

public class SotaResponse extends BaseResponse {
    String sessionId;

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
