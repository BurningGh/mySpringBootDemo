package com.example.datarest.model;

import lombok.Data;

import java.util.Date;

/**
 * @author lz
 * @date 2018/12/26
 */
@Data
public class Test {
    private Integer id;
    private String name;
    private Date birth;
    private Integer age;
}
