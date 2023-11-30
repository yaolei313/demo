package com.yao.app.demo.server.http.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

@Schema(description = "可修改的用户信息参数")
@ToString
public class UserModifyReqVo {

    @Schema(description = "昵称", maxLength = 32)
    private String nickname;

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    @JsonIgnore
    public boolean isValid() {
        if (StringUtils.isBlank(nickname) || nickname.length() > 32) {
            return false;
        }
        return true;
    }
}
