package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.enums.AgeEnum;
import com.example.mybatisplus.enums.GenderEnum;
import com.example.mybatisplus.enums.GradeEnum;
import com.example.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * Enums 演示
 *
 * @author lz
 * @date 2019/9/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EnumTest {
    @Resource
    private UserMapper mapper;


    @Test
    public void insert() {
        User user = new User();
        user.setName("K神");
//        user.setAge(AgeEnum.ONE);
//        user.setGrade(GradeEnum.HIGH);
//        user.setGender(GenderEnum.MALE);
        user.setEmail("abc@mp.com");
        Assert.assertTrue(mapper.insert(user) > 0);
        // 成功直接拿会写的 ID
        System.err.println("\n插入成功 ID 为：" + user.getId());

        List<User> list = mapper.selectList(null);
        for (User u : list) {
            System.out.println(u);
            Assert.assertNotNull("age should not be null", u.getAge());
            if (u.getId().equals(user.getId())) {
//                Assert.assertNotNull("gender should not be null", u.getGender());
//                Assert.assertNotNull("grade should not be null", u.getGrade());

            }
        }
    }
    @Test
    public void delete() {
        Assert.assertTrue(mapper.delete(new QueryWrapper<User>()
                .lambda().eq(User::getAge, AgeEnum.TWO)) > 0);
    }
//
//    @Test
//    public void update() {
//        Assert.assertTrue(mapper.update(new User().setAge(AgeEnum.TWO),
//                new QueryWrapper<User>().eq("age", AgeEnum.THREE)) > 0);
//    }

    @Test
    public void select() {
        User user = mapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 2));
        Assert.assertEquals("mp", user.getName());
        Assert.assertSame(AgeEnum.THREE, user.getAge());
    }
}
