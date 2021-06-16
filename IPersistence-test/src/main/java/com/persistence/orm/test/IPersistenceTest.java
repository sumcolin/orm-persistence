package com.persistence.orm.test;

import com.persistence.orm.dao.IUserDao;
import com.persistence.orm.io.Resources;
import com.persistence.orm.pojo.User;
import com.persistence.orm.session.SqlSessionFactory;
import com.persistence.orm.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/6
 */
public class IPersistenceTest {

    private IUserDao userDao;

    @Before
    public void before() throws Exception {
        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();

        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsSteam);

        userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
    }

    @Test
    public void test() throws Exception {
        // 加载资源文件
//        InputStream resourceAsSteam = Resources.getResourceAsSteam("sqlMapConfig.xml");
//        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
//
//        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(resourceAsSteam);
//
//        IUserDao userDao = sqlSessionFactory.openSession().getMapper(IUserDao.class);
//        User user = new User();
//        user.setId(1);
//        User user1 = userDao.selectOne(user);
//        System.out.println(user1.getUsername());
        List<User> users = userDao.selectAll();
        users.forEach(u -> {
            System.out.println(u.getUsername() + ":" + u.getBirthday());
        });
    }

    @Test
    public void testUpdate() throws Exception {
        User user = new User();
        user.setId(3);
        user.setUsername("joey");
        user.setBirthday("2020-01-01");
        user.setPassword("12345");
        userDao.updateById(user);
//        List<User> users = userDao.selectAll();
//        users.forEach(u -> {
//            System.out.println(u.getUsername() + ":" + u.getBirthday());
//        });
    }


    @Test
    public void testInsert() throws Exception {
        User user = new User();
        user.setId(3);
        user.setUsername("joey");
        user.setUsername("2020-01-01");
        user.setPassword("12345");
        userDao.insertUser(user);


    }


    @Test
    public void testDelete() throws Exception {
        User user = new User();
        user.setId(3);
        userDao.deleteUserById(user);


    }
}
