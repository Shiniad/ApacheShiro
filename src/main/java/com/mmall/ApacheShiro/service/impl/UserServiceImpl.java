package com.mmall.ApacheShiro.service.impl;

import com.mmall.ApacheShiro.mapper.UserMapper;
import com.mmall.ApacheShiro.model.User;
import com.mmall.ApacheShiro.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("myuserservice")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User findUserByUserName(String username){
        return userMapper.findUserByUserName(username);
    }
}
