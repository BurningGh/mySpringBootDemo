package com.example.shiro.service.impl;

import com.example.shiro.dao.UserInfoRepository;
import com.example.shiro.pojo.UserInfo;
import com.example.shiro.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * @author lz
 * @date 2018/8/23
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {
	
	@Resource
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserInfo findByUsername(String username) {
		System.out.println("UserInfoServiceImpl.findByUsername()");
		return userInfoRepository.findByUsername(username);
	}
	
}
