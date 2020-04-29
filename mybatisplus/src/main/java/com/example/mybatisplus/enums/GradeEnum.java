package com.example.mybatisplus.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 *  
 * @author lz
 * @date 2019/9/27
 */
public enum GradeEnum {

    PRIMARY(1, "小学"),
    SECONDORY(2, "中学"),
    HIGH(3, "高中");

    GradeEnum(int code, String descp) {
        this.code = code;
        this.descp = descp;
    }

    @EnumValue
    private final int code;
    private final String descp;

    public int getCode() {
        return code;
    }

    public String getDescp() {
        return descp;
    }
}
