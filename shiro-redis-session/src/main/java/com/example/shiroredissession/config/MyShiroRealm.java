package com.example.shiroredissession.config;

import com.example.shiroredissession.cache.MySimpleByteSource;
import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.entity.User;
import com.example.shiroredissession.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 处理授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user = (User) principal.getPrimaryPrincipal();
        for (Role role : user.getRoleList()) {
            authorizationInfo.addRole(role.getRole_name());
            for (Auth auth : role.getAuthList()) {
                authorizationInfo.addStringPermission(auth.getAuth_name());
            }
        }
        return authorizationInfo;
    }

    /**
     * 处理认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        // 1.从token里面获取用户名
        String username = (String) token.getPrincipal();
        // 2.从数据库查找该用户名，若失败，则抛异常
        User user = userService.queryUserByUserName(username);
        if (user == null) {
            return null;
        }
        // 3.查询成功则验证数据
        return new SimpleAuthenticationInfo(
                user,
                user.getPassword(),
                new MySimpleByteSource(username),
                getName()
        );
    }
}