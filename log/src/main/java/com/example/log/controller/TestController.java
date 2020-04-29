package com.example.log.controller;

import com.example.log.annotation.SysLog;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
public class TestController {
    @GetMapping
    @SysLog(title = "测试日志", action = "测试功能")
    public String log() {
        return "测试日志功能";
    }
}
