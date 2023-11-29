package com.yao.app.demo.server.http.interceptor;

import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.Results;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ExceptionAdvice.class);

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ResponseVo handleException(Exception e) {
        LOG.error("http request error.", e);
        return Results.FAIL;
    }

}
