package com.yqx.service;

import com.yqx.pojo.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

// security 框架
public interface UserService extends UserDetailsService {


    /**
     * Day03 查询所有用户信息
     * @return
     * @AUTO 于清晰
     */
    List<UserInfo> findAll();

    /**
     * Day03 添加用户信息
     * @param userInfo
     * @Auto yuqingxi
     */
    void save(UserInfo userInfo);

    /**
     * Day04 用户详情信息展示
     * @param id
     * @return
     * @Auto 于清晰
     */
    UserInfo findById(String id);

    /**
     * Day04 根据userId获取用户
     * 然后根据ids获取所有的角色信息
     * @param userId
     * @param ids
     * @return
     * @Auto 于清晰
     */
    void addRoleToUser(String userId, String[] ids);
}
