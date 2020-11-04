package com.yqx.controller;


import com.yqx.pojo.Product;
import com.yqx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * Day01 查询所有信息
     * Product表
     * @return
     * @Auto yuqingxi
     */
    // 如果想访问这个方法 那么必须拥有USER权限
    @RolesAllowed( "ADMIN" )
    @RequestMapping("/findAll")
    public ModelAndView findAll(){
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = productService.findAll();
        modelAndView.addObject( "productList",productList );
        modelAndView.setViewName( "product-list" );
        return modelAndView;
    }


    /**
     * Day02 添加方法
     * Product表
     * @param product
     * @return
     * @Auto yuqingxi
     */
    @RequestMapping( "/save" )
    public String save( Product product ){
        // 保存方法
        productService.save( product );

        // 执行完保存方法之后 跳转到查询所有的方法中
        return "redirect:findAll.do";
    }







}
