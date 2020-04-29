package com.example.shiroredissession.web;

import com.example.shiroredissession.entity.User;
import com.example.shiroredissession.service.UserService;
import com.example.shiroredissession.utils.QueryUserNameUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ShiroController {

    private final UserService userService;

    public ShiroController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/")
    public String gotoIndexPage(Model model) {
        String username = QueryUserNameUtils.getUserName();
        model.addAttribute("username", username);
        return "/index";
    }


    @RequestMapping(value = "/login")
    public String doLogin(HttpServletRequest request, Map<String, Object> map) {
        /* 1.shiro自动验证
         *由于已经在shiro里面配置了登录url为 /login 所以一旦触发此链接，
         * 就会先去MyShiroRealm验证登录，如果验证成功则直接执行配置好的地址
         * 若失败，则走以下过程
         * */

        // 2.检查是否验证出错
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        String msg = "";
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                msg = "UnknownAccountException -- > 账号不存在：";
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                msg = "IncorrectCredentialsException -- > 密码不正确：";
            } else if ("kaptchaValidateFailed".equals(exception)) {
                System.out.println("kaptchaValidateFailed -- > 验证码错误");
                msg = "kaptchaValidateFailed -- > 验证码错误";
            } else {
                msg = "else >> " + exception;
                System.out.println("else -- >" + exception);
            }
        }
        map.put("msg", msg);
        // 出错则留在此页面，并提示错误信息
        return "/login";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }

    @RequestMapping("/register")
    public String gotoRegistPage(User user) {
        if (user.getUsername() != null) {
            userService.saveOneUser(user);
            return "/login";
        }
        return "/register";
    }
}
