package com.example.validator.controller;

import com.example.validator.framework.validgroup.AddGroup;
import com.example.validator.model.Person;
import com.example.validator.model.Result;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * 人员信息控制器
 *
 * @author lz
 * @date 2019/9/6
 */
@Validated
@RestController
@RequestMapping("/person")
public class PersonController {

    /**
     * 校验单个参数方式，一定要在控制器类上加Validated注解
     *
     * @param personId
     * @return
     */
    @DeleteMapping("/{personId}/remove")
    public Result remove(@PathVariable("personId") @NotNull(message = "personId不能为空") @Min(value = 1, message = "personId必须大于0") Long personId) {
        return Result.success().setData(personId);
    }


    /**
     * 通过对整个person对象进行验证，需要在person类中加入验证条件<br/>
     * 可以在验证条件上加入groups参数，表明是在什么情况下进行这条验证<br/>
     * 具体方式请参考：https://blog.csdn.net/wangpeng047/article/details/41726299
     *
     * @param person
     * @return
     */
    @PostMapping("/add")
    public Result addSave(@Validated({AddGroup.class}) Person person) {
        return Result.success().setData(person);
    }

    @PutMapping("/{personId}/edit")
    public Result editSave(@PathVariable("personId") @NotNull(message = "personId不能为空") @Min(value = 1, message = "personId必须大于0") Long personId, @Valid Person person) {
        return Result.success().setData(person);
    }
}
