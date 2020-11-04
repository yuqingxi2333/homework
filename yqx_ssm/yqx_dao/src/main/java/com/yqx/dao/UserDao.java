package com.yqx.dao;

import com.yqx.pojo.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface UserDao {
    /**
     * Day03 根据用户id查询信息
     * @param id
     * @return
     * @throws Exception
     * @Auto 于清晰
     */
    @Select("select * from users where id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.yqx.dao.RoleDao.findRoleByUserId")) })
     UserInfo findById(String id) ;
//    public UserInfo findById(Long id) ;


    /**
     * Day03 根据用户名查询信息
     * @param username
     * @return
     * @Auto 于清晰
     */
    @Select("select * from users where username=#{username}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(column = "username", property = "username"),
            @Result(column = "email", property = "email"),
            @Result(column = "password", property = "password"),
            @Result(column = "phoneNum", property = "phoneNum"),
            @Result(column = "status", property = "status"),
            @Result(column = "id", property = "roles", javaType = List.class,
                    many = @Many(select = "com.yqx.dao.RoleDao.findRoleByUserId")) })
    public UserInfo findByUsername(String username);


    /**
     * Day03 查询所有用户信息
     * @return
     * @AUTO 于清晰
     */
    @Select("select * from users")
    List<UserInfo> findAll();


    /**
     * Day03 添加用户信息
     * @param userInfo
     * @Auto yuqingxi
     */
    @Insert( "insert into users (username,password,email,phoneNum,status) values (#{username},#{password},#{email},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    /**
     * Day04 根据userId获取用户
     * 然后根据ids获取所有的角色信息
     * @param userId
     * @param ids
     * @return
     * @Auto 于清晰
     */
    @Insert( "insert into users_role( userId,roleId ) values ( #{userId},#{roleId} )")
    void addRoleToUser(@Param("userId") String userId,@Param("roleId") String ids);
}
