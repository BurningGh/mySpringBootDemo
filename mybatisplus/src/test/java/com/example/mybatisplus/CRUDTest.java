package com.example.mybatisplus;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.enums.AgeEnum;
import com.example.mybatisplus.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CRUDTest {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

    @Test
    public void bDelete() {
        assertThat(userMapper.deleteById(3L)).isGreaterThan(0);
        assertThat(userMapper.delete(new QueryWrapper<User>().lambda().eq(User::getName, "Sandy"))).isGreaterThan(0);
    }

    @Test
    public void aInsert() {
        User user = new User();
        user.setName("小羊");
        user.setAge(2);
        user.setEmail("abc@mp.com");
        assertThat(userMapper.insert(user)).isGreaterThan(0);
        // 成功直接拿会写的 ID
        assertThat(user.getId()).isNotNull();
    }

    @Test
    public void cUpdate() {
        assertThat(userMapper.updateById(new User().setId(1L).setEmail("ab@c.c"))).isGreaterThan(0);
        assertThat(userMapper.update(new User().setName("mp"), Wrappers.<User>lambdaUpdate().set(User::getAge, 3).eq(User::getId, 2))).isGreaterThan(0);
        User user = userMapper.selectById(2);
        assertThat(user.getAge()).isEqualTo(3);
        assertThat(user.getName()).isEqualTo("mp");


        userMapper.update(null, Wrappers.<User>lambdaUpdate().set(User::getEmail, null).eq(User::getId, 2));
        assertThat(userMapper.selectById(1).getEmail()).isEqualTo("ab@c.c");
        user = userMapper.selectById(2);
        assertThat(user.getEmail()).isNull();
        assertThat(user.getName()).isEqualTo("mp");

        userMapper.update(new User().setEmail("miemie@baomidou.com"), new QueryWrapper<User>().lambda().eq(User::getId, 2));
        user = userMapper.selectById(2);
        assertThat(user.getEmail()).isEqualTo("miemie@baomidou.com");

        userMapper.update(new User().setEmail("miemie2@baomidou.com"), Wrappers.<User>lambdaUpdate().set(User::getAge, null).eq(User::getId, 2));
        user = userMapper.selectById(2);
        assertThat(user.getEmail()).isEqualTo("miemie2@baomidou.com");
        assertThat(user.getAge()).isNull();
    }


    @Test
    public void dSelect() {
        userMapper.insert(new User().setId(10086L).setName("miemie").setEmail("miemie@baomidou.com").setAge(3));

        assertThat(userMapper.selectById(10086L).getEmail()).isEqualTo("miemie@baomidou.com");


        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getId, 10086));
        assertThat(user.getName()).isEqualTo("miemie");
        assertThat(user.getAge()).isEqualTo(3);

        userMapper.selectList(Wrappers.<User>lambdaQuery().select(User::getId))
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNull();
                    assertThat(x.getAge()).isNull();
                });
        userMapper.selectList(new QueryWrapper<User>().select("id", "name"))
                .forEach(x -> {
                    assertThat(x.getId()).isNotNull();
                    assertThat(x.getEmail()).isNull();
                    assertThat(x.getName()).isNotNull();
                    assertThat(x.getAge()).isNull();
                });
    }


    @Test
    public void orderBy() {
        List<User> users = userMapper.selectList(Wrappers.<User>query().orderByAsc("age"));
        assertThat(users).isNotEmpty();
    }


    @Test
    public void selectMaps() {
        List<Map<String, Object>> mapList = userMapper.selectMaps(Wrappers.<User>query().orderByAsc("age"));
        assertThat(mapList).isNotEmpty();
        assertThat(mapList.get(0)).isNotEmpty();
        System.out.println(mapList.get(0));
    }

    @Test
    public void selectMapsPage() {
        IPage<Map<String, Object>> page = userMapper.selectMapsPage(new Page<>(1, 5), Wrappers.<User>query().orderByAsc("age"));
        assertThat(page).isNotNull();
        assertThat(page.getRecords()).isNotEmpty();
        assertThat(page.getRecords().get(0)).isNotEmpty();
        System.out.println(page.getRecords().get(0));
    }

    @Test
    public void orderByLambda() {
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByAsc(User::getAge));
        assertThat(users).isNotEmpty();
    }

    @Test
    public void testSelectMaxId() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("max(id) as id");
        User user = userMapper.selectOne(wrapper);
        System.out.println("maxId=" + user.getId());
        List<User> users = userMapper.selectList(Wrappers.<User>lambdaQuery().orderByDesc(User::getId));
        Assert.assertEquals(user.getId().longValue(), users.get(0).getId().longValue());
    }

    @Test
    public void testGroup() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(*)").groupBy("age");
        List<Map<String, Object>> mapList = userMapper.selectMaps(wrapper);
        for (Map<String, Object> mp : mapList) {
            System.out.println(mp);
        }
        /**
         * lambdaQueryWrapper groupBy orderBy
         */
        LambdaQueryWrapper<User> lambdaQueryWrapper = new QueryWrapper<User>().lambda()
                .select(User::getAge)
                .groupBy(User::getAge)
                .orderByAsc(User::getAge);
        for (User user : userMapper.selectList(lambdaQueryWrapper)) {
            System.out.println(user);
        }
    }


    @Test
    public void testTableFieldExistFalse() {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.select("age, count(age) as count")
                .groupBy("age");
        List<User> list = userMapper.selectList(wrapper);
        list.forEach(System.out::println);
        list.forEach(x -> {
            Assert.assertNull(x.getId());
            Assert.assertNotNull(x.getAge());
            Assert.assertNotNull(x.getCount());
        });
        userMapper.insert(
                new User().setId(10088L)
                        .setName("miemie")
                        .setEmail("miemie@baomidou.com")
                        .setAge(2));
        User miemie = userMapper.selectById(10088L);
        Assert.assertNotNull(miemie);

    }
}
