package com.example.shiro.service;

import com.example.shiro.pojo.UserInfo;
/**
 * @author lz
 * @date 2018/8/23
 *
 */
public interface UserInfoService {
	
	/**通过username查找用户信息;*/
	public UserInfo findByUsername(String username);
	
}
