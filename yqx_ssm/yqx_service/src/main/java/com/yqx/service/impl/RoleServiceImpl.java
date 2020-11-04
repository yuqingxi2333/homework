package com.yqx.service.impl;

import com.yqx.dao.RoleDao;
import com.yqx.pojo.Role;
import com.yqx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    /**
     * Day04 查询所有角色信息
     * Role表
     * @return
     * @Auto 于清晰
     */
    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    /**
     * Day04 添加功能
     * 添加角色信息到Role表中
     * @param role
     */
    @Override
    public void save(Role role) {
        roleDao.save( role );
    }

    /**
     * Day04 根据传入的id 查找到对应的用户 及可以添加的角色
     * 一个用户可以有多个角色 多对多
     * @param id
     * @return
     * @Auto 于清晰
     */
    @Override
    public List<Role> findOtherRole(String id) {
        return roleDao.findOtherRole(id);
    }

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @return
     * @Auto 于清晰
     * @param roleId
     */
    @Override
    public Role findById(String roleId) {
        return roleDao.findById(roleId);
    }

    /**
     * Day04 添加权限给用户
     * @param roleId
     * @param permissionIds
     * @return
     * @Auto 于清晰
     */
    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole( roleId,permissionId );
        }
    }
}
