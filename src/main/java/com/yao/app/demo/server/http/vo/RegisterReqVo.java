package com.yao.app.demo.server.http.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;

@Schema(description = "用户注册参数")
@ToString
public class RegisterReqVo {

    @Schema(required = true, description = "昵称", maxLength = 32)
    private String nickname;

    @Schema(required = true, description = "邮箱", maxLength = 64)
    private String email;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }
}
