package com.mybatis.orm;

import com.mybatis.orm.dao.IOrderDao;
import com.mybatis.orm.dao.IUserDao;
import com.mybatis.orm.pojo.Order;
import com.mybatis.orm.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

/**
 * @auth 邹新
 * @email 741779841@qq.com
 * @date 2021/6/7
 */
public class MybatisTest {

    private IOrderDao orderMapper;

    private IUserDao userMapper;

//    RoundingMode

    @Before
    public void before() throws Exception {
        // 1、解析配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2、创建session工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取mapper表相关数据
        orderMapper = sqlSession.getMapper(IOrderDao.class);
        userMapper = sqlSession.getMapper(IUserDao.class);
    }

    @Test
    public void test() throws Exception {
        // 1、解析配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2、创建session工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sessionFactory.openSession();
        List<User> users = sqlSession.selectList("user.selectAll");

        for (User user : users) {
            System.out.println(user.getUsername() + ":" + user.getBirthday());
        }
    }


    @Test
    public void test2() throws Exception {

        // 1、解析配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 2、创建session工厂
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sessionFactory.openSession();
        // 3、获取mapper表相关数据
        IOrderDao mapper = sqlSession.getMapper(IOrderDao.class);
        // 4、获取数据
        Order order1 = mapper.selectOrderAndUser("1");
        System.out.println(order1.toString());
    }


    /**
     * 持久层一对一测试
     * @throws Exception
     */
    @Test
    public void test3() {
        Order order1 = orderMapper.selectOrderById("1");
        System.out.println(order1.toString());
    }


    /**
     * 持久层一对多测试
     * @throws Exception
     */
    @Test
    public void test4() {
        User user = userMapper.selectOne("1");
//        Order order1 = orderMapper.selectOrderById("1");
        System.out.println(user.toString());
    }
}
