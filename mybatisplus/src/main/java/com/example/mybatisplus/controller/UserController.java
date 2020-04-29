package com.example.mybatisplus.controller;


import com.example.mybatisplus.model.User;
import com.example.mybatisplus.service.IUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lz
 * @since 2019-09-26
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> list() {
        return userService.list();
    }
}
