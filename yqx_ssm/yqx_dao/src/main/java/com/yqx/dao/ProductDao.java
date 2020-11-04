package com.yqx.dao;

import com.yqx.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

// DAO 持久层接口
@Repository
public interface ProductDao {

    /**
     * Day01
     * 查询所有用户信息
     *
     * @return
     * @Auto Yqx
     */
    @Select("select * from product")
    List<Product> findAll();


    /**
     * Day02 保存方法
     * Product表
     *
     * @param product
     * @Auto yuqingxi
     */
    @Insert("insert into product" +
            "(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) " +
            "values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice}," +
            "#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     * Day02 根据id查询数据
     * Product表
     *
     * @param id
     * @return
     * @Auto yuqingxi
     */
    @Select("select * from product where id=#{id}")
    Product findById(String id);
}
