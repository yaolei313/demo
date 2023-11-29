package com.yao.app.demo.util;

public enum ResultCodeEnum {
    SUCCESS(0, "success"),
    FAIL(-1, "fail"),
    INVALID_REQUEST(-100, "invalid request"),
    EMAIL_REGISTERED(-101, "email already registered"),
    ;

    private int code;

    private String message;

    private ResultCodeEnum(int code, String message) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
