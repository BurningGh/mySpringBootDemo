package com.example.kafka;

import lombok.Data;

import java.io.Serializable;

/**
 * 人员实体
 *
 * @author lz
 * @date 2019/10/11
 */
@Data
public class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
}
