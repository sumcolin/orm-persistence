package com.persistence.orm.dao;

import com.persistence.orm.pojo.User;

import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public interface IUserDao {

    List<User> selectAll ();

    User selectOne (User user);

    void updateById (User user);


    void insertUser (User user);


    void deleteUserById (User user);
}
