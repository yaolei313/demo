package com.yao.app.demo.util;

import java.util.HashMap;
import java.util.Map;

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

    private static final Map<Byte, UserStatusEnum> MAP = new HashMap<>();

    static {
        for (UserStatusEnum item : UserStatusEnum.values()) {
            MAP.put(item.getCode(), item);
        }
    }

    public static UserStatusEnum fromCode(byte value) {
        return MAP.get(value);
    }
}
