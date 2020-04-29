package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author miemie
 * @since 2018-08-10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaginationTest {

    @Resource
    private UserMapper mapper;

    @Test
    public void lambdaPagination() {
        Page<User> page = new Page<>(1, 3);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.lambda().ge(User::getAge, 1).orderByAsc(User::getAge);
        IPage<User> result = mapper.selectPage(page, wrapper);
        System.out.println(result.getTotal());
        Assert.assertTrue(result.getTotal() > 3);
        Assert.assertEquals(3, result.getRecords().size());
    }
}
