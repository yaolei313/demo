package com.yao.app.demo.server.http.controller;

import com.yao.app.demo.biz.UserService;
import com.yao.app.demo.server.http.vo.RegisterReqVo;
import com.yao.app.demo.server.http.vo.RegisterRspVo;
import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.UserInfoVo;
import com.yao.app.demo.server.http.vo.UserModifyReqVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "用户")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "注册一个新用户")
    @RequestMapping(path = "/users", method = RequestMethod.POST)
    public ResponseVo<RegisterRspVo> create(@RequestBody RegisterReqVo vo) {
        return userService.register(vo);
    }

    @Operation(summary = "查看用户信息")
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public ResponseVo<UserInfoVo> retrieve(@PathVariable Long userId) {
        return userService.query(userId);
    }

    @Operation(summary = "修改用户信息,允许直接修改的字段暂时只有昵称")
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.POST)
    public ResponseVo update(@PathVariable Long userId, @RequestBody UserModifyReqVo vo) {
        return userService.modify(userId, vo);
    }

    @Operation(summary = "删除用户")
    @RequestMapping(path = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseVo delete(@PathVariable Long userId) {
        return userService.deactivateAccount(userId);
    }

    @Operation(summary = "批量删除用户,最多允许传入10个id")
    @RequestMapping(path = "/users/batch_deactivate_account", method = RequestMethod.POST)
    public ResponseVo batchDelete(@RequestParam("user_ids") List<Long> userIds) {
        return userService.batchDeactivateAccount(userIds);
    }
}
