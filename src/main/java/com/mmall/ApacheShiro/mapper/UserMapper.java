package com.mmall.ApacheShiro.mapper;

import com.mmall.ApacheShiro.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    User findUserByUserName(@Param("username") String username);
}
