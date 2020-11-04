package com.yqx.service.impl;

import com.yqx.dao.ProductDao;
import com.yqx.pojo.Product;
import com.yqx.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

// Service 业务逻辑层的实现类对象
@Service        // 使用注解配置Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    /**
     * Day01
     * 查询所有用户信息
     * @return
     * @Auto Yqx
     */
    @Override
    public List<Product> findAll() {
        System.out.println( "查询所有信息!" );
        return productDao.findAll();
    }

    /**
     * Day02 保存方法
     * Product表
     * @param product
     * @Auto yuqingxi
     */
    @Override
    public void save(Product product) {
        productDao.save( product );
    }
}
