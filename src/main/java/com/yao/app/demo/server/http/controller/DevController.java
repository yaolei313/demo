package com.yao.app.demo.server.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DevController {

    @RequestMapping(path = "/status", method = RequestMethod.GET)
    public String status() {
        return "ok";
    }
}
