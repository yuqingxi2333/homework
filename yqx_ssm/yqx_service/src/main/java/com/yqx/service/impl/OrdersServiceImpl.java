package com.yqx.service.impl;

import com.github.pagehelper.PageHelper;
import com.yqx.dao.OrdersDao;
import com.yqx.pojo.Orders;
import com.yqx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;

    /**
     * Day02 订单分页功能
     * @param page
     * @param pageSize
     * @return
     * @Auto yuqingxi
     */
    @Override
    public List<Orders> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        return ordersDao.findAll();
    }

    /**
     * Day03 根据id查询单条数据
     * @param id
     * @return
     * @Auto yuqingxi
     */
    @Override
    public Orders findById(String id) {

        return ordersDao.findById( id );
    }
}
