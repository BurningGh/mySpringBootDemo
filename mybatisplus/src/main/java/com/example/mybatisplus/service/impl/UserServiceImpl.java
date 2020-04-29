package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.User;
import com.example.mybatisplus.dao.UserMapper;
import com.example.mybatisplus.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lz
 * @since 2019-09-26
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
