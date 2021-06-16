package com.mybatis.orm.dao;

import com.mybatis.orm.pojo.Order;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/10
 */
public interface IOrderDao {

    public Order selectOrderAndUser (String id);

    public Order  selectOrderById(String id);
}
