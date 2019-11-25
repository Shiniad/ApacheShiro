package com.mmall.ApacheShiro.controller;

import com.mmall.ApacheShiro.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class UserController {
    @Resource(name = "myuserservice")
    private UserService userService;

    @GetMapping("/test")
    public Object test(String username){
        return userService.findUserByUserName(username);
    }
}
