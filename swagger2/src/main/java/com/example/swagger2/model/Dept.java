package com.example.swagger2.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 部门
 *
 * @author lz
 * @date 2019/12/26
 */
@ApiModel(value = "部门")
@Data
public class Dept {
    @ApiModelProperty(value = "部门名称")
    private String name;
    @ApiModelProperty(value = "描述")
    private String desc;
}
