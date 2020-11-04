package com.yqx.service;

import com.yqx.pojo.Product;

import java.util.List;

// Service 业务层接口
public interface ProductService {

    /**
     *  Day 01
     * 查询 Product 表中所有的信息
     * @return
     * @Auto    Yqx
     */
    List<Product> findAll();

    /**
     * Day02 保存方法
     * Product表
     * @param product
     * @Auto yuqingxi
     */
    void save(Product product);
}
