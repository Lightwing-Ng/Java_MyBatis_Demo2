package com.lightwing.mybatis.test;

import com.lightwing.mybatis.dao.UserDao;
import com.lightwing.mybatis.pojo.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.fail;

public class UserDaoTest {
    private ApplicationContext applicationContext;

    @Before
    public void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void testGetUserById() {
        UserDao userDao = applicationContext.getBean(UserDao.class);
        User user = userDao.getUserById(10074);
        System.out.println(user);
    }

    @Test
    public void testGetUserByUserName() {
        fail("Not yet implemented");
    }

    @Test
    public void testInsertUser() {
        fail("Not yet implemented");
    }
}
