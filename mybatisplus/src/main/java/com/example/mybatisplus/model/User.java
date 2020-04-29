package com.example.mybatisplus.model;

import com.baomidou.mybatisplus.annotation.*;
import com.example.mybatisplus.enums.AgeEnum;
import com.example.mybatisplus.enums.GenderEnum;
import com.example.mybatisplus.enums.GradeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author lz
 * @since 2019-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄<br/>
     * IEnum接口的枚举处理
     */
//    private AgeEnum age;
    private Integer age;
    /**
     * 原生枚举： 默认使用枚举值顺序： 0：MALE， 1：FEMALE
     */
//    private GenderEnum gender;

    /**
     * 原生枚举（带{@link com.baomidou.mybatisplus.annotation.EnumValue}):
     * 数据库的值对应该注解对应的属性
     */
//    private GradeEnum grade;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer delFlag;

    @TableField(exist = false)
    private Integer count;

    @Version
    private Integer version;
}
