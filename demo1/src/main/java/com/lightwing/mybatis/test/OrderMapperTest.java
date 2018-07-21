package com.lightwing.mybatis.test;

import com.lightwing.mybatis.mapper.OrderMapper;
import com.lightwing.mybatis.pojo.Order;
import com.lightwing.mybatis.pojo.OrderUser;
import com.lightwing.mybatis.utils.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class OrderMapperTest {
    @Test
    public void testGetOrderList() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取 OrderMapper 代理实现
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> list = orderMapper.getOrderList();
        for (Order order : list)
            System.out.println(order);

        sqlSession.close();
    }

    @Test
    public void testGetOrderListMap() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取 OrderMapper 代理实现
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> list = orderMapper.getOrderListMap();
        for (Order order : list)
            System.out.println(order + "\n");

        sqlSession.close();
    }

    @Test
    public void testGetOrderUser() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取 OrderMapper 代理实现
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<OrderUser> list = orderMapper.getOrderUser();
        for (OrderUser orderUser : list) {
            System.out.println(orderUser + "\n");
        }

        sqlSession.close();
    }

    @Test
    public void testGetOrderUserMap() {
        SqlSession sqlSession = SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
        // 获取 OrderMapper 代理实现
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);

        List<Order> list = orderMapper.getOrderUserMap();
        for (Order order : list) {
            System.out.println(order);
            System.out.println("    Ordered By User " + order.getUser() + "\n");
        }
        sqlSession.close();
    }
}
