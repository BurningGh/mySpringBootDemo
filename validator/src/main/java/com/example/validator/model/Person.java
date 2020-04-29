package com.example.validator.model;

import com.example.validator.framework.validgroup.AddGroup;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 人员信息
 *
 * @author lz
 * @date 2019/6/21
 */
@Data
public class Person {
    private static final long serialVersionUID = 2L;
    /**
     * id
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date createdAt;
    /**
     * 更新时间
     */
    private Date updatedAt;
    /**
     * 人像库id
     */
    @NotNull(message = "人像库ID不能为空")
    @Min(value = 1, message = "人像库ID必须大于0" ,groups = {AddGroup.class})
    private Long faceRepositoryId;
    /**
     * 人员姓名
     */
    @NotBlank(message = "人员姓名不能为空")
    private String name;

    /**
     * 年龄
     */
    @Range(min = 0, max = 130, message = "人员年龄不正确")
    private Integer age;

    /**
     * 状态
     */
    private Integer status;
    /**
     * 是否已经删除
     */
    private boolean delFlag = false;

}
