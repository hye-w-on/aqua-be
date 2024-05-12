package com.aqua.aquabe.exception;

import com.aqua.aquabe.constants.StatusCodeConstants;

public class BusinessException extends RuntimeException {

    private final String statusCode;
    private final String message;

    public BusinessException() {
        this(StatusCodeConstants.FAIL, "");
    }

    public BusinessException(String statusCode) {
        this(statusCode, "");
    }

    public BusinessException(String statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public BusinessException(String statusCode, String message, Throwable cause) {
        super(cause);
        this.statusCode = statusCode;
        this.message = message;
    }

    public BusinessException(String message, Throwable cause) {
        this(StatusCodeConstants.FAIL, message, cause);
    }

    public String getMessage() {
        return this.message;
    }

    public String getStatusCode() {
        return this.statusCode;
    }
}
