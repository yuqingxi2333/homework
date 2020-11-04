package com.yqx.dao;

import com.yqx.pojo.Orders;
import com.yqx.pojo.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrdersDao {

    /**
     * Day02 订单分页功能
     * @return
     * @Auto yuqingxi
     */
    /*@Select("select * from orders")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",
                    one = @One(select = "com.yqx.dao.ProductDao.findById")) })
    List<Orders> findAll();*/

    @Select("select * from orders")
    @Results({ @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",javaType = Product.class,
                    one = @One(select = "com.yqx.dao.ProductDao.findById")) })
    List<Orders> findAll();

    /**
     * Day03 根据id查询单条数据
     * @param id
     * @return
     * @Auto 于清晰
     */
    @Select("select * from orders where id=#{id}")
    @Results({ @Result(id=true,column = "id",property = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",
                    one = @One(select = "com.yqx.dao.ProductDao.findById")),
            @Result(column = "id",property = "travellers",
                    many = @Many(select = "com.yqx.dao.TravellerDao.findByOrdersId")),
            @Result(column = "memberId",property = "member",
                    one = @One(select = "com.yqx.dao.MemberDao.findById")), })
    Orders findById(String id);


}
