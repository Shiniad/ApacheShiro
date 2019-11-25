package com.mmall.ApacheShiro;

import com.mmall.ApacheShiro.model.Permission;
import com.mmall.ApacheShiro.model.Role;
import com.mmall.ApacheShiro.model.User;
import com.mmall.ApacheShiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    @Qualifier("myuserservice")
    private UserService userService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 在已登录的情况下授权（可以session中得到用户信息，也可principalCollection得到）
        // 对pincipalCollection用迭代器遍历迭代获得用户对象
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<String>();
        Set<Role> roles = user.getRoles();
        if(!CollectionUtils.isEmpty(roles)){
            for (Role role : roles) {
                Set<Permission> permissions = role.getPermissions();
                if(!CollectionUtils.isEmpty(permissions)){
                    for (Permission permission : permissions) {
                        // 对用户对象循环遍历获得授权数据
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        // 组装AuthorizationInfo对象
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        return info;
    }

    // 认证登录
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 使用用户密码token类获取用户名
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.findUserByUserName(username);
        // 组装认证器，返回认证器（校验info和token）
        return new SimpleAuthenticationInfo(user,user.getPassword(),this.getClass().getName());
    }
}
