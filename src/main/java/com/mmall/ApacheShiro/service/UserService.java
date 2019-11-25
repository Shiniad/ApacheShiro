package com.mmall.ApacheShiro.service;

import com.mmall.ApacheShiro.model.User;

public interface UserService {
    User findUserByUserName(String username);
}
