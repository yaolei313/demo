package com.yao.app.demo.server.http.vo;

import com.yao.app.demo.util.ResultCodeEnum;
import org.apache.commons.lang3.StringUtils;

public class Results {

    // --工具方法--

    public static <T> ResponseVo<T> success(T data) {
        return new ResponseVo(ResultCodeEnum.SUCCESS.getCode(), ResultCodeEnum.SUCCESS.getMessage(), data);
    }

    public static <T> ResponseVo<T> fail(ResultCodeEnum code, String message) {
        if (StringUtils.isBlank(message)) {
            message = code.getMessage();
        }
        return new ResponseVo(code.getCode(), message, null);
    }

    public static final ResponseVo SUCCESS = success(null);

    public static final ResponseVo FAIL = fail(ResultCodeEnum.FAIL, null);

    public static final ResponseVo INVALID_REQUEST = fail(ResultCodeEnum.INVALID_REQUEST, "invalid request");

    public static final ResponseVo EMAIL_REGISTERED = fail(ResultCodeEnum.EMAIL_REGISTERED, null);
}
