package com.example.shiroredissession.service.impl;

import com.example.shiroredissession.dao.UserDao;
import com.example.shiroredissession.entity.Auth;
import com.example.shiroredissession.entity.Role;
import com.example.shiroredissession.entity.User;
import com.example.shiroredissession.service.UserService;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> queryUserList() {
        List<User> userList = userDao.queryAllUsers();
        List<User> users = new ArrayList<>();
        for (User user:userList) {
            // 2.查找用户对应的角色
            List<Role> roleList = new ArrayList<>();
            Integer role_id = userDao.queryRoleIdByUserId(user.getId());
            Role role = userDao.queryRoleByRoleId(role_id);

            // 3.查找角色对应的auth
            List<Integer> authIdList = null;
            try {
                authIdList = userDao.queryAuthIdByRoleId(role.getId());
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("查询不到用户权限Id");
            }
            List<Auth> authList = new ArrayList<>();
            if (authIdList != null) {
                for (Integer id:authIdList) {
                    Auth auth = null;
                    try {
                        auth = userDao.queryAuthByAuthId(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("没有权限信息");
                    }
                    authList.add(auth);
                }
            }

            // 4.组装
            if (role != null) {
                role.setAuthList(authList);
            }
            roleList.add(role);
            user.setRoleList(roleList);
            users.add(user);
        }
        return users;
    }

    @Override
    public User queryUserByUserName(String username) {
        // 1.查询用户
        User user = userDao.queryUserByUsername(username);

        if (user == null) {
            throw new UnknownAccountException();
        }

        // 2.查找用户对应的角色
        List<Role> roleList = new ArrayList<>();
        Integer role_id = userDao.queryRoleIdByUserId(user.getId());
        Role role = userDao.queryRoleByRoleId(role_id);

        // 3.查找角色对应的auth
        List<Integer> authIdList = null;
        try {
            authIdList = userDao.queryAuthIdByRoleId(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询不到用户权限Id");
        }
        List<Auth> authList = new ArrayList<>();
        if (authIdList != null) {
            for (Integer id:authIdList) {
                Auth auth = null;
                try {
                    auth = userDao.queryAuthByAuthId(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("没有权限信息");
                }
                authList.add(auth);
            }
        }

        // 4.组装
        if (role != null) {
            role.setAuthList(authList);
        }
        roleList.add(role);
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public User queryUserById(Integer Id) {
        // 1.查询用户
        User user = userDao.queryUserById(Id);

        if (user == null) {
            throw new UnknownAccountException();
        }

        // 2.查找用户对应的角色
        List<Role> roleList = new ArrayList<>();
        Integer role_id = userDao.queryRoleIdByUserId(user.getId());
        Role role = userDao.queryRoleByRoleId(role_id);

        // 3.查找角色对应的auth
        List<Integer> authIdList = null;
        try {
            authIdList = userDao.queryAuthIdByRoleId(role.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("查询不到用户权限Id");
        }
        List<Auth> authList = new ArrayList<>();
        if (authIdList != null) {
            for (Integer id:authIdList) {
                Auth auth = null;
                try {
                    auth = userDao.queryAuthByAuthId(id);
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("没有权限信息");
                }
                authList.add(auth);
            }
        }

        // 4.组装
        if (role != null) {
            role.setAuthList(authList);
        }
        roleList.add(role);
        user.setRoleList(roleList);
        return user;
    }

    @Override
    public void saveOneUser(User user) {
        try {
            userDao.saveOneUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("======注册失败======");
        }
    }

    @Override
    public void delOneUserById(Integer id) {
        userDao.delOneUserById(id);
    }
}
