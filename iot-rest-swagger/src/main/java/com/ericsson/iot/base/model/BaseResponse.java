package com.ericsson.iot.base.model;

import org.springframework.http.HttpStatus;

public class BaseResponse {
    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    HttpStatus status;
    String result;
    String errorMessage;

}
