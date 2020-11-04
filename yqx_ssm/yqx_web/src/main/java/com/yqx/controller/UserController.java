package com.yqx.controller;

import com.yqx.pojo.Role;
import com.yqx.pojo.UserInfo;
import com.yqx.service.RoleService;
import com.yqx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping( "/user" )
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Day04 注入角色表
     * 用户可以有多个角色
     * @Auto 于清晰
     */
    @Autowired
    private RoleService roleService;


    /**
     * Day03  添加用户信息
     * @param userInfo
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/save" )
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:/user/findAll.do";
    }

    /**
     *  Day03 查询所有用户信息
     * @return
     * @Auto yuqingxi
     */
    @RequestMapping( "/findAll" )
    public ModelAndView findAll(){
        List<UserInfo> userInfoList = userService.findAll();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject( "userList",userInfoList );
        modelAndView.setViewName( "user-list" );
        return modelAndView;
    }

    /**
     * Day04 用户页面 点击详情查询用户信息
     * 需要根据用户的id去查询所有的信息
     * @param id
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/findById" )
    public ModelAndView findById(@RequestParam( name = "id",required = true) String id){
        ModelAndView modelAndView = new ModelAndView();
        UserInfo user = userService.findById( id );
        modelAndView.addObject( "user",user );
        modelAndView.setViewName( "user-show" );
        return modelAndView;
    }

    /**
     * Day04 根据传入的id 查找到对应的用户 及可以添加的角色
     * 一个用户可以有多个角色 多对多
     * @param id
     * @return
     */
    @RequestMapping( "/findUserByIdAndAllRole" )
    public ModelAndView findUserByIdAndAllRole( String id ){
        // 根据id查找到单个用户
        UserInfo user = userService.findById(id);
        // 根据用户的id  查找到该用户对应的所有角色
        List<Role> roleList = roleService.findOtherRole( id );
        ModelAndView modelAndView = new ModelAndView();
        // 将获取到的数据 发送给前台页面
        modelAndView.addObject( "user",user );
        modelAndView.addObject( "roleList",roleList );
        modelAndView.setViewName( "user-role-add" );
        return modelAndView;
    }

    /**
     * Day04 根据userId获取用户
     * 然后根据ids获取所有的角色信息
     * @param userId
     * @param ids
     * @return
     * @Auto 于清晰
     */
    @RequestMapping( "/addRoleToUser" )
    public String addRoleToUser( String userId,String[] ids ){
        userService.addRoleToUser( userId,ids );
//        return "redirect:/user/findAll.do";
        return "redirect:findAll.do";
    }






}
