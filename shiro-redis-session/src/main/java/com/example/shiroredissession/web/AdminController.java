package com.example.shiroredissession.web;

import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.entity.User;
import com.example.shiroredissession.service.*;
import com.example.shiroredissession.utils.QueryUserNameUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;
    private final UserService userService;
    private final RoleService roleService;
    private final AuthService authService;
    private final UserRoleAuthService userRoleAuthService;

    public AdminController(AdminService adminService, UserService userService, RoleService roleService, AuthService authService, UserRoleAuthService userRoleAuthService) {
        this.adminService = adminService;
        this.userService = userService;
        this.roleService = roleService;
        this.authService = authService;
        this.userRoleAuthService = userRoleAuthService;
    }

    @RequestMapping("/queryAllUser")
    @RequiresPermissions("user:admin")
    public String queryAllUser(Model model) {
        List<User> users = userService.queryUserList();
        model.addAttribute("users", users);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/admin/manageUsers";
    }

    @RequestMapping("/delete")
    @RequiresPermissions("user:admin")
    public String delOneUserById(@RequestParam("id") Integer id) {
        userService.delOneUserById(id);
        return "redirect:/admin/queryAllUser";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String updateOneUserById(@RequestParam("user_id") Integer user_id,
                                    @RequestParam("role_id") Integer role_id,
                                    @RequestParam("auth_id") Integer auth_id) {
        // 1.往user_role里面插入数据
        if (!user_id.equals(role_id)) {
            userRoleAuthService.saveUserRole(user_id, role_id);
        }
        // 2.往role_auth里面插入数据
        if (!role_id.equals(auth_id)) {
            userRoleAuthService.saveRoleAuth(role_id, auth_id);
        }
        return "1";
    }

    @RequestMapping("/updateOneUserPage")
    @RequiresPermissions("user:admin")
    public String gotoUpdateOneUserPage(@RequestParam("id") Integer id, Model model) {
        User user = userService.queryUserById(id);
        model.addAttribute("user", user);
        List<Role> roleList = roleService.queryRoleList();
        model.addAttribute("roleList", roleList);
        List<Auth> authList = authService.queryAuthList();
        model.addAttribute("authList", authList);
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/admin/updateOneUser";
    }

    @RequestMapping("/delete/role")
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String delRole(@RequestParam("user_id") Integer user_id
            , @RequestParam("role_id") Integer role_id) {
        userRoleAuthService.delRole(user_id, role_id);
        return "1";
    }

    @RequestMapping("/delete/auth")
    @ResponseBody
    @RequiresPermissions("user:admin")
    public String delAuth(@RequestParam("auth_id") Integer auth_id
            , @RequestParam("role_id") Integer role_id) {
        userRoleAuthService.delAuth(role_id, auth_id);
        return "1";
    }

}
