package com.yqx.controller;

import com.yqx.pojo.Permission;
import com.yqx.pojo.Role;
import com.yqx.service.PermissionService;
import com.yqx.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Permissions;
import java.util.List;

// 角色类控制器
@Controller
@RequestMapping( "/role" )
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
     * Day04 查询所有角色信息
     * Role表
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/findAll" )
    public ModelAndView findAll(){
        List<Role> roleList = roleService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "roleList",roleList );
        modelAndView.setViewName( "role-list" );
        return modelAndView;
    }

    /**
     * Day04 添加方法
     * 添加角色到Role表中
     * @param role
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/save" )
    public String save( Role role ){
        roleService.save( role );
        return "redirect:findAll.do";
    }

    /**
     * Day04 根据角色id查询角色之后 获取可添加的权限
     * @param roleId
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/findRoleByIdPermission" )
    public ModelAndView findRoleByIdPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView modelAndView = new ModelAndView();
        Role role = roleService.findById( roleId );
        modelAndView.addObject( "role",role );

        // 测试 可删除
        modelAndView.addObject( "roleId",roleId );


        // 根据角色id 查询出所有可添加的权限
        List<Permission> permissionList = permissionService.findOtherPermission( roleId );
        modelAndView.addObject( "permissionList",permissionList );
        modelAndView.setViewName( "role-permission-add" );
        return modelAndView;
    }

    /**
     * Day04 添加权限给用户
     * @param roleId
     * @param permissionIds
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/addPermissionToRole" )
    public String addPermissionToRole( @RequestParam( name = "roleId",required = true)String roleId,
                                       @RequestParam( name = "ids",required = true)String[] permissionIds){
        roleService.addPermissionToRole( roleId,permissionIds );
        return "redirect:findAll.do";
    }


}
