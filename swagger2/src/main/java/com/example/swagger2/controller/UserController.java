package com.example.swagger2.controller;

import com.example.swagger2.model.Dept;
import com.example.swagger2.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 用户
 *
 * @author lz
 * @date 2019/12/26
 */
@RestController
@RequestMapping(value = "/user")
@Api(tags = "用户")
public class UserController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }


    private final static Map<Integer, User> users = new LinkedHashMap<Integer, User>();

    {
        users.put(1, new User(1, "admin", "6666", "166@163.com", "hehe", new Date(), 1, new Dept()));
        users.put(2, new User(2, "admin", "6666", "166@163.com", "hehe", new Date(), 2, new Dept()));
    }


    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public Map<String, Object> userList() {
        List<User> userList = new ArrayList<>(users.values());
        Map<String, Object> map = new HashMap<>();
        map.put("data", userList);
        map.put("code", 200);
        return map;
    }

    @ApiOperation("新增用户")
    @PostMapping("/save")
    public Map<String, Object> save(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        return map;
    }
}
