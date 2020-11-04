package com.yqx.service.impl;

import com.yqx.dao.PermissionDao;
import com.yqx.pojo.Permission;
import com.yqx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;
    /**
     * Day04 资源权限查询
     * 查询所有 Permission表
     * @return
     */
    @Override
    public List<Permission> findAll() {
        return permissionDao.findAll();
    }

    /**
     * Day04 权限添加
     * Permission表
     * @param permission
     * @return
     * @Auto 于清晰
     */
    @Override
    public void save(Permission permission) {
       permissionDao.save( permission );
    }

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @param roleId
     * @return
     * @Auto 于清晰
     */
    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return permissionDao.findOtherPermission(roleId);
    }


}
