package com.lightwing.mybatis.test;

import com.lightwing.ssm.mapper.UserMapper;
import com.lightwing.ssm.po.User;
import com.lightwing.ssm.po.UserExample;
import com.lightwing.ssm.po.UserExample.Criteria;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

import static org.junit.Assert.fail;

public class UserMapperGTest {

    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testInsertSelective() {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = new User();
        user.setUsername("Huning Wang");
        user.setAddress("Cenzhou");
        user.setSex("F");
        userMapper.insertSelective(user);
    }

    @Test
    public void testSelectByExample() {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        UserExample example = new UserExample();
        //创建Criteria
        Criteria criteria = example.createCriteria();

        //设置查询条件
        criteria.andUsernameLike("%Li%");
        criteria.andSexEqualTo("F");
        List<User> list = userMapper.selectByExample(example);
        for (User user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void testSelectByPrimaryKey() {
        UserMapper userMapper = applicationContext.getBean(UserMapper.class);
        User user = userMapper.selectByPrimaryKey(10074);
        System.out.println(user);
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        System.out.println("Failed");
        fail("Not yet implemented");
    }
}
