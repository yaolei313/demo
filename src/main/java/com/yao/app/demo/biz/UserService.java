package com.yao.app.demo.biz;

import com.yao.app.demo.dependency.mysql.bean.User;
import com.yao.app.demo.dependency.mysql.mapper.UserMapper;
import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.Results;
import com.yao.app.demo.server.http.vo.UserRegisterVo;
import com.yao.app.demo.util.UserStatusEnum;
import com.yao.app.demo.util.ValidateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;

    @Autowired
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public ResponseVo<Long> register(UserRegisterVo info) {
        // validate
        ResponseVo err = validateRegister(info);
        if (err != null) {
            LOG.info("invalid register request: {}", err);
            return err;
        }

        // use timestamp avoid timezone
        long now = System.currentTimeMillis();

        // insert db
        User record = new User();
        record.setEmail(info.getEmail());
        record.setNickname(info.getNickname());
        record.setStatus(UserStatusEnum.NORMAL.getCode());
        record.setCreateTime(now);
        record.setUpdateTime(now);
        userMapper.insert(record);

        LOG.info("register success. userId:{}", record.getId());

        return Results.success(record.getId());
    }

    private ResponseVo validateRegister(UserRegisterVo user) {
        if (user == null) {
            return Results.INVALID_REQUEST;
        }
        if (!ValidateUtils.validateEmail(user.getEmail()) || user.getEmail().length() > 64) {
            return Results.ILLEGAL_EMAIL;
        }
        if (StringUtils.isBlank(user.getNickname()) || user.getNickname().length() > 32) {
            return Results.ILLEGAL_NICKNAME;
        }
        Long userId = userMapper.selectByEmail(user.getEmail());
        if (userId != null) {
            return Results.EMAIL_REGISTERED;
        }
        return null;
    }
}
