package com.yqx.controller;

import com.yqx.pojo.Permission;
import com.yqx.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 权限控制器
@Controller
@RequestMapping( "/permission" )
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    /**
     * Day04 资源权限查询
     * 查询所有 Permission表
     * @return
     */
    @RequestMapping( "/findAll" )
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = permissionService.findAll();
        modelAndView.addObject( "permissionList",permissionList );
        modelAndView.setViewName( "permission-list" );
        return modelAndView;
    }

    /**
     * Day04 权限添加
     * Permission表
     * @param permission
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/save" )
    public String save( Permission permission ){
        permissionService.save( permission );
        return "redirect:findAll.do";
    }





}
