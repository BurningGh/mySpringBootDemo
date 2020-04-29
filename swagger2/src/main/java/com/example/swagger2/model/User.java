package com.example.swagger2.model;

import com.example.swagger2.annotation.IgnoreSwaggerParameter;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@ApiModel(value = "用户")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @ApiModelProperty(value = "用户id", example = "1")
    private Integer id;
    @ApiModelProperty(value = "用户名", example = "admin")
    private String username;
    @ApiModelProperty(value = "密码", example = "123456")
    private String password;
    @ApiModelProperty(value = "邮箱", example = "aa@163.com")
    private String email;
    @ApiModelProperty(value = "昵称", example = "test")
    private String nickname;
    @ApiModelProperty(value = "生日", example = "2018-01-01 00:00:00")
    private Date birth;
    @ApiModelProperty(value = "部门id", example = "1")
    private Integer deptId;
    @ApiModelProperty(value = "部门信息")
    @IgnoreSwaggerParameter // 在不需要递归展开的属性上加上IgnoreSwaggerParameter注解
    private Dept dept;
}