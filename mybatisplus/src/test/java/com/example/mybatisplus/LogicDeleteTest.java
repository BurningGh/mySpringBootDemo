package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * 逻辑删除
 *
 * @author lz
 * @date 2019/9/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class LogicDeleteTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void testLogicDeleteById() {
        userMapper.deleteById(1);
    }

    @Test
    public void testLogicDeleteBatchIds() {
        userMapper.deleteBatchIds(Arrays.asList(1, 2, 3));
    }

    @Test
    public void testLogicDelete() {
        userMapper.delete(new QueryWrapper<User>().eq("age", 2));
    }

}
