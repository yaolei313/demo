package com.yao.app.demo.server.http.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.yao.app.demo.util.ValidateUtils;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

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

    @JsonIgnore
    public boolean isValid() {
        if (StringUtils.isBlank(nickname) || nickname.length() > 32) {
            return false;
        }
        if (!ValidateUtils.isValidEmail(email) || email.length() > 64) {
            return false;
        }
        return true;
    }
}
