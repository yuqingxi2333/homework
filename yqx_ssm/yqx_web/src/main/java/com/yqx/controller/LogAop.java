package com.yqx.controller;

import com.yqx.pojo.SysLog;
import com.yqx.service.SysLogService;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import java.lang.reflect.Method;
import java.util.Date;
import javax.servlet.http.*;

// 创建切面 完成日志记录功能
// @Component
// @Aspect // 代表这是一个切面
public class LogAop {

    // 注入Service
    @Autowired
    private SysLogService sysLogService;
    // 注入request
    @Autowired
    private HttpServletRequest request;

    // 定义变量写在外面
    private Date startTime; // 访问时间
    private Class executionClass;   // 访问的类
    private Method executionMethod; // 访问的方法


    /**
     * Day05 日志记录
     * 找到com.yqx.controller下面的所有方法 进行拦截
     * @param joinpoint
     * @Auto 于清晰
     */
    @Before( "execution(* com.yqx.controller.*.*(..))" )
    public void doBefore(JoinPoint joinpoint) throws NoSuchMethodException {
        // 获取当前访问的时间
        startTime = new Date();
        // 获取要访问的类  找到被访问的类
        executionClass = joinpoint.getTarget().getClass();
        // 找到被访问类中的方法
        String methodName = joinpoint.getSignature().getName();
        // 获取访问方法的参数
        Object[] args = joinpoint.getArgs();

        // 进行判断
        // 如果遍历出来没有参数的话 那么代表这是无参的方法 执行无参的方法
        if ( args == null || args.length == 0){
            // 根据方法名称 反射获取方法
            executionMethod = executionClass.getMethod( methodName );
        }else{
            // 否则就是有参数 那么要遍历args中所有的元素
            Class[] classArgs = new Class[args.length];
            // 循环遍历
            for (int i = 0; i < classArgs.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            // 最后获取方法名并返回
            executionMethod = executionClass.getMethod( methodName,classArgs );
        }
    }

    @After( "execution(* com.yqx.controller.*.*(..))" )
    public void doAfter( JoinPoint joinPoint ){
        // 获取类上的 RequestMapping对象
        // 判断是否是自己本身 如果是就没必要执行了
        if ( executionClass != SysLogController.class ){
            // 获取注解
            RequestMapping classAnnotation = (RequestMapping) executionClass.getAnnotation(RequestMapping.class);
            // 再次判断 如果注解不为空的话
            if ( classAnnotation!=null ){
                // 获取到方法上的注解
                RequestMapping methodAnnotation = executionMethod.getAnnotation(RequestMapping.class);
                // 如果方法上的注解也不为空的话
                if ( methodAnnotation!=null ){
                    // 创建一个空字符串
                    String url = "";
                    url = classAnnotation.value()[0] + methodAnnotation.value()[0];

                    // 获取访问时长
                    SysLog sysLog = new SysLog();
                    Long executionTime = new Date().getTime() - startTime.getTime();
                    // 进行封装
                    sysLog.setExecutionTime( executionTime );
                    sysLog.setUrl( url );
                    // 获取访问电脑ip值
                    String ip = request.getRemoteAddr();
                    sysLog.setIp( ip );

                    // 获取username
                    SecurityContext context = SecurityContextHolder.getContext();
                    String username =  ((User) (context.getAuthentication().getPrincipal())).getUsername();
                    sysLog.setUsername( username );

                    // 获取访问的内容
                    sysLog.setMethod("[类名]" + executionClass.getName() + "[方法名]" + executionMethod.getName());
                    sysLog.setVisitTime(startTime);
                    // 调用service 调用dao将数据保存到数据库中\
                    sysLogService.save( sysLog );
                }
            }
        }
    }
}
