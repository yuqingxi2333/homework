package com.yqx.dao;

import com.yqx.pojo.Permission;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// 权限控制 实现
@Repository
public interface PermissionDao {

    /**
     * Day04 资源权限查询
     * 查询所有 Permission表
     * @return
     */
    @Select( "select * from permission" )
    List<Permission> findAll();

    /**
     * Day04 权限添加
     * Permission表
     * @param permission
     * @return
     * @Auto 于清晰
     */
    @Insert( "insert into permission(id,permissionName,url) values (permission_seq.nextval,#{permissionName},#{url})")
    void save(Permission permission);

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @param roleId
     * @return
     * @Auto 于清晰
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findOtherPermission(String roleId);

}
