package com.yqx.service.impl;

import com.yqx.dao.UserDao;
import com.yqx.pojo.Role;
import com.yqx.pojo.UserInfo;
import com.yqx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

// 用户权限登录
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    // 用户密码加密
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    /**
     * Day03 用户权限登录方法
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     * @Auto 于清晰
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 使用UserInfo功能完成操作
        UserInfo userInfo = userDao.findByUsername(username);
        User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,true,true,
                true, getAuthority(userInfo.getRoles()));
       /* User user = new User(userInfo.getUsername(), userInfo.getPassword(),
                getAuthority());*/
        return user;
    }

    /**
     * Day03    用户权限管理
     * @return
     * @Auto 于清晰
     */
    private Collection<SimpleGrantedAuthority> getAuthority() {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));
        return list;
    }


    /**
     * Day03    用户权限管理
     * @return
     * @Auto 于清晰
     */
    private Collection<SimpleGrantedAuthority> getAuthority(List<Role> rolesList) {
        List<SimpleGrantedAuthority> list = new ArrayList<SimpleGrantedAuthority>();
        for (Role role : rolesList) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }


    /**
     * Day03 查询所有用户信息
     * @return
     * @AUTO 于清晰
     */
    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }


    /**
     * Day03 添加用户信息
     * @param userInfo
     * @Auto yuqingxi
     */
    @Override
    public void save(UserInfo userInfo) {
        // 加密密码
        userInfo.setPassword( passwordEncoder.encode(userInfo.getPassword()) );
        userDao.save( userInfo );
    }

    /**
     * Day04 用户详情信息展示
     * @param id
     * @return
     * @Auto 于清晰
     */
    @Override
    public UserInfo findById(String id) {
        return userDao.findById( id );
    }

    /**
     * Day04 根据userId获取用户
     * 然后根据ids获取所有的角色信息
     * @param userId
     * @param ids
     * @return
     * @Auto 于清晰
     */
    @Override
    public void addRoleToUser(String userId, String[] ids) {
        for (String id : ids) {
            userDao.addRoleToUser( userId,id);
        }
    }
}
