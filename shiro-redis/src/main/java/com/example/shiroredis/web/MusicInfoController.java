package com.example.shiroredis.web;

import com.example.shiroredis.dao.domain.SysUser;
import com.example.shiroredis.service.UserService;
import com.example.shiroredis.utils.RequestUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;


@Controller
@RequestMapping(value = "/music")
public class MusicInfoController {

    private final UserService userService;

    public MusicInfoController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/login")
    @RequiresPermissions({"/usersPage"})
    @ResponseBody
    public String login(){
        SysUser user = RequestUtils.currentLoginUser();
        Set<String> authorization = userService.findPermissionsByUserId(user.getId());
        return "该用户有如下权限" + authorization;
    }

}
