package com.yao.app.demo.biz;

import com.yao.app.demo.dependency.mysql.bean.EmailPassport;
import com.yao.app.demo.dependency.mysql.bean.User;
import com.yao.app.demo.dependency.mysql.mapper.EmailPassportMapper;
import com.yao.app.demo.dependency.mysql.mapper.UserMapper;
import com.yao.app.demo.server.http.vo.RegisterReqVo;
import com.yao.app.demo.server.http.vo.RegisterRspVo;
import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.Results;
import com.yao.app.demo.server.http.vo.UserInfoVo;
import com.yao.app.demo.server.http.vo.UserModifyReqVo;
import com.yao.app.demo.util.Converter;
import com.yao.app.demo.util.UserStatusEnum;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    private UserMapper userMapper;

    private EmailPassportMapper emailPassportMapper;

    private MailService mailService;

    private IdGenerator idGenerator;

    @Autowired
    public UserService(UserMapper userMapper, EmailPassportMapper emailPassportMapper,
        MailService mailService, IdGenerator idGenerator) {
        this.userMapper = userMapper;
        this.emailPassportMapper = emailPassportMapper;
        this.mailService = mailService;
        this.idGenerator = idGenerator;
    }

    /**
     * 考虑账号删除需要清理一些信息，故拆分表，且使用id生成避免影响写入顺序。
     */
    public ResponseVo<RegisterRspVo> register(RegisterReqVo req) {
        // parameter validate && biz check
        if (req == null || !req.isValid()) {
            LOG.info("invalid req. {}", req);
            return Results.INVALID_REQUEST;
        }

        Long dbUserId = emailPassportMapper.selectUserIdByEmail(req.getEmail());
        if (dbUserId != null) {
            return Results.EMAIL_REGISTERED;
        }

        // use timestamp avoid timezone
        long now = System.currentTimeMillis();

        // user id生成
        long userId = idGenerator.generateUserId();

        // insert db
        EmailPassport passport = new EmailPassport();
        passport.setUserId(userId);
        passport.setEmail(req.getEmail());
        emailPassportMapper.insert(passport);

        User record = new User();
        record.setUserId(userId);
        record.setEmail(req.getEmail());
        record.setNickname(req.getNickname());
        record.setStatus(UserStatusEnum.NORMAL.getCode());
        record.setCreateTime(now);
        record.setUpdateTime(now);
        userMapper.insert(record);

        LOG.info("register success. userId:{}", userId);

        mailService.sendWelcomeRegisterEmailQuietly(req.getEmail(), req.getNickname());

        return Results.success(new RegisterRspVo(userId));
    }

    public ResponseVo<UserInfoVo> query(Long userId) {
        if (userId == null || userId <= 0) {
            return Results.INVALID_REQUEST;
        }

        User record = userMapper.selectByUserId(userId);
        return Results.success(Converter.convertToUserInfoVo(record));
    }

    public ResponseVo modify(Long userId, UserModifyReqVo req) {
        if (userId == null || userId <= 0 || req == null || !req.isValid()) {
            return Results.INVALID_REQUEST;
        }

        User record = new User();
        record.setUserId(userId);
        record.setNickname(req.getNickname());
        record.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByUserId(record);

        return Results.SUCCESS;
    }

    public ResponseVo deactivateAccount(Long userId) {
        if (userId == null || userId <= 0) {
            return Results.INVALID_REQUEST;
        }

        deactivate(userId);

        return Results.SUCCESS;
    }

    public ResponseVo batchDeactivateAccount(List<Long> userIds) {
        if (CollectionUtils.isEmpty(userIds) || userIds.size() > 10) {
            return Results.INVALID_REQUEST;
        }

        for (Long userId : userIds) {
            deactivateAccount(userId);
        }

        return Results.SUCCESS;
    }

    private void deactivate(Long userId) {
        User user = userMapper.selectByUserId(userId);
        if (user == null) {
            return;
        }
        emailPassportMapper.deleteByEmail(user.getEmail());

        User record = new User();
        record.setUserId(userId);
        record.setNickname("已注销");
        record.setEmail("");
        record.setStatus(UserStatusEnum.DEACTIVATE.getCode());
        record.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByUserId(record);

        LOG.info("deactivate account {}", userId);
    }
}
