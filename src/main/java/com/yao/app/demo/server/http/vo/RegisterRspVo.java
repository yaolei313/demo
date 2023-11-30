package com.yao.app.demo.server.http.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@Schema(description = "用户注册成功后返回的信息")
@ToString
public class RegisterRspVo {

    @Schema(description = "用户id")
    @JsonSerialize(using = ToStringSerializer.class)
    private long userId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public RegisterRspVo() {
    }

    public RegisterRspVo(long userId) {
        this.userId = userId;
    }
}
