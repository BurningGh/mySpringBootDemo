package com.example.shiroredissession.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {

    public Integer id;

    public String username;

    public String password;

    public String salt;

    // 一个用户有多个角色、对应user_role表
    public List<Role> roleList;

    /**
     * 密码盐.
     * 重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
     *
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}
