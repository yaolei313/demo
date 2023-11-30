package com.yao.app.demo.util;

import com.yao.app.demo.dependency.mysql.bean.User;
import com.yao.app.demo.server.http.vo.UserInfoVo;

// TODO use mapstruct to map bean
public class Converter {

    public static UserInfoVo convertToUserInfoVo(User p) {
        if (p == null) {
            return null;
        }
        UserInfoVo result = new UserInfoVo();
        result.setUserId(p.getUserId());
        result.setNickname(p.getNickname());
        result.setEmail(p.getEmail());
        result.setStatus(UserStatusEnum.fromCode(p.getStatus()));
        result.setRegisterTime(p.getCreateTime());
        return result;
    }
}
