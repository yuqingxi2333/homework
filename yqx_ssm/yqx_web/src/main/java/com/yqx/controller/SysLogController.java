package com.yqx.controller;

import com.yqx.pojo.SysLog;
import com.yqx.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 日志控制器
@Controller
@RequestMapping( "/sysLog" )
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping( "/findAll" )
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<SysLog> sysLogs = sysLogService.findAll();
        modelAndView.addObject( "sysLogs",sysLogs );
        modelAndView.setViewName( "syslog-list" );
        return modelAndView;
    }

}
