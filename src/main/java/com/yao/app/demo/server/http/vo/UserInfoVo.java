package com.yao.app.demo.server.http.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.yao.app.demo.util.UserStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "用户信息参数")
public class UserInfoVo {

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "用户id")
    private long userId;

    @Schema(description = "用户昵称")
    private String nickname;

    @Schema(description = "用户email")
    private String email;

    @Schema(description = "用户状态")
    private UserStatusEnum status;

    @JsonSerialize(using = ToStringSerializer.class)
    @Schema(description = "用户注册时间")
    private Long registerTime;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    public Long getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Long registerTime) {
        this.registerTime = registerTime;
    }
}
