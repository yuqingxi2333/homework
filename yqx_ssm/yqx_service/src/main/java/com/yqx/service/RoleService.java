package com.yqx.service;

import com.yqx.pojo.Role;

import java.util.List;

// 角色表
public interface RoleService {

    /**
     * Day04 查询所有角色信息
     * Role表
     * @return
     * @Auto 于清晰
     */
    List<Role> findAll();

    /**
     * Day04 添加功能
     * 添加角色信息到Role表中
     * @param role
     * @Auto 于清晰
     */
    void save(Role role);

    /**
     * Day04 根据传入的id 查找到对应的用户 及可以添加的角色
     * 一个用户可以有多个角色 多对多
     * @param id
     * @return
     * @Auto 于清晰
     */
    List<Role> findOtherRole(String id);

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @param roleId
     * @return
     * @Auto 于清晰
     */
    Role findById(String roleId);

    /**
     * Day04 添加权限给用户
     * @param roleId
     * @param permissionIds
     * @return
     * @Auto 于清晰
     */
    void addPermissionToRole(String roleId, String[] permissionIds);
}
