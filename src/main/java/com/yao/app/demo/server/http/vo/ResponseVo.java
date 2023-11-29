package com.yao.app.demo.server.http.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ResponseVo<T> {

    @Schema(description = "结果码")
    private int code;

    @Schema(description = "提示信息")
    private String message;

    @Schema(description = "数据")
    private T data;

    public ResponseVo() {
    }

    public ResponseVo(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
