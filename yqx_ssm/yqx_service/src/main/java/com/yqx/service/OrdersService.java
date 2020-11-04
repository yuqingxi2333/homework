package com.yqx.service;

import com.yqx.pojo.Orders;

import java.util.List;

public interface OrdersService {

    /**
     * Day02 订单分页功能
     * @param page
     * @param pageSize
     * @return
     * @Auto yuqingxi
     */
    List<Orders> findAll(Integer page, Integer pageSize);

    /**
     * Day03 根据id查询单条数据
     * 点击单条数据 查询该数据下的详细信息
     * @param id
     * @return
     * @Auto yuqingxi
     */
    Orders findById(String id);
}
