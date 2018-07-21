package com.lightwing.mybatis.test;

import com.lightwing.mybatis.mapper.UserMapper;
import com.lightwing.mybatis.pojo.Order;
import com.lightwing.mybatis.pojo.QueryVo;
import com.lightwing.mybatis.pojo.User;
import com.lightwing.mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class UserMapperTest {
    @Test
    public void testGetUserById() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = userMapper.getUserById(10074);
        System.out.println(user);
        sqlSession.close();
    }

    @Test
    public void testGetUserByUserName() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        List<User> list = userMapper.getUserByUserName("Jake");
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testInsertUser() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();

        user.setUsername("Aomark Bao");
        user.setSex("F");
        user.setBirthday(new Date());
        user.setAddress("Hong Kong");

        userMapper.insertUser(user);

        sqlSession.commit();
        sqlSession.close();
    }


    @Test
    public void testGetUserByQueryVo() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo vo = new QueryVo();
        User user2 = new User();
        user2.setUsername("ok");
        vo.setUser(user2);

        List<User> list = userMapper.getUserByQueryVo(vo);
        for (User user : list) {
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testGetUserCount() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

        Integer userCount = userMapper.getUserCount();
        System.out.println("Total Users: " + userCount);
        sqlSession.close();
    }

    @Test
    public void testGetUserByPojo() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setSex("M");
        user.setUsername("oj");

        List<User> list = userMapper.getUserByPojo(user);
        for (User user2 : list)
            System.out.println(user2);

        sqlSession.close();
    }

    @Test
    public void testGetUserByIds() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        QueryVo vo = new QueryVo();

        // 构建id列表
        vo.setIds(Arrays.asList(34523, 82535, 85243, 42675, 54353));
        List<User> list = userMapper.getUserByIds(vo);
        for (User user2 : list)
            System.out.println(user2);
        sqlSession.close();
    }

    @Test
    public void testGetUserOrderMap() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        //获取接口的代理人实现类
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = userMapper.getUserOrderMap();
        for (User user2 : list) {
            System.out.println(user2);
            for (Order order : user2.getOrders())
                if (order.getId() != null)
                    System.out.println("This User has: " + order);
        }
        sqlSession.close();
    }
}
