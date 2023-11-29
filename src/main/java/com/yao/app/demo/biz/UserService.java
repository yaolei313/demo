package com.yao.app.demo.biz;

import com.yao.app.demo.dependency.mysql.bean.User;
import com.yao.app.demo.dependency.mysql.mapper.UserMapper;
import com.yao.app.demo.server.http.vo.RegisterReqVo;
import com.yao.app.demo.server.http.vo.RegisterRspVo;
import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.Results;
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

    private MailService mailService;

    @Autowired
    public UserService(UserMapper userMapper, MailService mailService) {
        this.userMapper = userMapper;
        this.mailService = mailService;
    }

    public ResponseVo<RegisterRspVo> register(RegisterReqVo req) {
        // parameter validate && biz check
        ResponseVo err = validateRegisterReq(req);
        if (err != null) {
            LOG.info("invalid register request: {}", err);
            return err;
        }

        // use timestamp avoid timezone
        long now = System.currentTimeMillis();

        // insert db
        User record = new User();
        record.setEmail(req.getEmail());
        record.setNickname(req.getNickname());
        record.setStatus(UserStatusEnum.NORMAL.getCode());
        record.setCreateTime(now);
        record.setUpdateTime(now);
        userMapper.insert(record);

        LOG.info("register success. userId:{}", record.getId());

        mailService.sendWelcomeRegisterEmailQuietly(req.getEmail(), req.getNickname());

        return Results.success(new RegisterRspVo(record.getId()));
    }

    // ----

    private ResponseVo validateRegisterReq(RegisterReqVo req) {
        if (req == null) {
            return Results.INVALID_REQUEST;
        }
        if (!ValidateUtils.validateEmail(req.getEmail()) || req.getEmail().length() > 64) {
            return Results.ILLEGAL_EMAIL;
        }
        if (StringUtils.isBlank(req.getNickname()) || req.getNickname().length() > 32) {
            return Results.ILLEGAL_NICKNAME;
        }
        Long userId = userMapper.selectByEmail(req.getEmail());
        if (userId != null) {
            return Results.EMAIL_REGISTERED;
        }
        return null;
    }
}
