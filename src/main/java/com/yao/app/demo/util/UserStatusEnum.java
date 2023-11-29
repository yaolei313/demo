package com.yao.app.demo.util;

public enum UserStatusEnum {
    NORMAL(0),
    DEACTIVATE(1),
    ;

    private int code;

    UserStatusEnum(int code) {
        this.code = code;
    }

    public byte getCode() {
        return (byte) code;
    }
}
