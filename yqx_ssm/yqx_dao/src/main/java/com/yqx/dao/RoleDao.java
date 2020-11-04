package com.yqx.dao;

import com.yqx.pojo.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// 角色表
@Repository
public interface RoleDao {

    /**
     * Day03 根据userId查询角色
     * @return
     * @Auto 于清晰
     */
    @Select( "select * from role where id in(select roleid from users_role where userid=#{userid})" )
    Role findRoleByUserId( String userid );

    /**
     * Day04 查询所有角色信息
     * Role表
     * @return
     * @Auto 于清晰
     */
    @Select( "select * from role" )
    List<Role> findAll();

    /**
     * Day04 添加功能
     * 添加角色信息到Role表中
     * 字段有两个
     * @param role
     */
    @Insert( "insert into role(id,roleName,roleDesc) values (role_seq.nextval,#{roleName},#{roleDesc})")
    void save(Role role);

    /**
     * Day04 根据传入的id 查找到对应的用户 及可以添加的角色
     * 一个用户可以有多个角色 多对多
     * @param id
     * @return
     * @Auto 于清晰
     */
    @Select( "select * from role where id not in( select roleId from users_role where userId= #{id})")
    List<Role> findOtherRole(String id);

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @return
     * @Auto 于清晰
     * @param roleId
     */
    @Select( "select * from role where id = #{roleId} ")
    Role findById(String roleId);

    /**
     * Day04 添加权限给用户
     * @param roleId
     * @param permissionIds
     * @return
     * @Auto 于清晰
     */
    @Insert( "insert into role_permission(roleid,permissionid) values (#{roleid},#{permissionid})" )
    void addPermissionToRole(@Param("roleid") String roleid, @Param("permissionid") String permissionid);
}
