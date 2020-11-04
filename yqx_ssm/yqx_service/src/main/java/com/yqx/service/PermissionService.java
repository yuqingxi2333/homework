package com.yqx.service;

import com.yqx.pojo.Permission;

import java.util.List;

// 权限控制
public interface PermissionService {

    /**
     * Day04 资源权限查询
     * 查询所有 Permission表
     * @return
     */
    List<Permission> findAll();

    /**
     * Day04 权限添加
     * Permission表
     * @param permission
     * @return
     * @Auto 于清晰
     */
    void save(Permission permission);

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @param roleId
     * @return
     * @Auto 于清晰
     */
    List<Permission> findOtherPermission(String roleId);


}
