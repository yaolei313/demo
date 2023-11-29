package com.yao.app.demo.server.http.controller;

import com.yao.app.demo.biz.UserService;
import com.yao.app.demo.server.http.vo.ResponseVo;
import com.yao.app.demo.server.http.vo.UserInfoVo;
import com.yao.app.demo.server.http.vo.UserRegisterVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public ResponseVo<Long> create(@RequestBody UserRegisterVo vo) {
        return userService.register(vo);
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.GET)
    public ResponseVo<UserInfoVo> retrieve(@PathVariable Long userId) {
        return null;
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.POST)
    public ResponseVo update(@PathVariable Long userId, @RequestBody UserInfoVo vo) {
        return null;
    }

    @RequestMapping(path = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseVo delete(@PathVariable Long userId) {
        return null;
    }
}
