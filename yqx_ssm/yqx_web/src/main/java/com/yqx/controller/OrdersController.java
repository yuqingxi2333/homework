package com.yqx.controller;

import com.github.pagehelper.PageInfo;
import com.yqx.pojo.Orders;
import com.yqx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

// 订单查询
@Controller
@RequestMapping( "/orders" )
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * /**
     * Day02 订单分页
     * 查询所有信息
     * @param page      当前页码
     * @param pageSize  每页显示的条数
     * @return
     * @Auto yuqingxi
     */
    // 必须写全才好使
    @Secured( "ROLE_ADMIN" )
    @RequestMapping( "/findAll.do" )
    public ModelAndView findAll(@RequestParam(name = "page", required = true, defaultValue = "1") Integer page,
                                @RequestParam(name = "pageSize", required = true, defaultValue = "4") Integer pageSize){
        List<Orders> ordersList = ordersService.findAll(page, pageSize);
        PageInfo pageInfo = new PageInfo(ordersList);
        ModelAndView mv = new ModelAndView();

        mv.addObject("pageInfo", pageInfo);
        mv.setViewName("orders-list");
        return mv;
    }

    /**
     * Day03 根据id查询数据
     * @return
     * @Auto yuqingxi
     */
    @RequestMapping( "/findById" )
    public ModelAndView findById( String id ){
        // 创建视图
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = ordersService.findById( id );
        // 共享到jsp页面
        // 将查询到的单条数据 发送到orders-show页面 获取所有数据
        modelAndView.setViewName( "orders-show" );
        modelAndView.addObject( "orders",orders );
        return modelAndView;
    }


}

