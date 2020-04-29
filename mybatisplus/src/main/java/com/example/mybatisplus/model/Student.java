package com.example.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学生实体 
 * @author lz
 * @date 2019/9/27
 */
@Data
@TableName(value = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @TableId
    private Long id;

    private String name;

    private Integer age;

}
