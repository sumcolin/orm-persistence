package com.mybatis.orm.dao;

import com.mybatis.orm.pojo.User;

import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/7
 */
public interface IUserDao {

    List<User> selectAll();


    User selectOne(String id);

}
