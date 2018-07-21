package com.lightwing.mybatis.dao;

import com.lightwing.mybatis.pojo.User;

import java.util.List;

/**
 * 用户信息持久化接口
 *
 * @author Lightwing Ng
 */
public interface UserDao {

    /**
     * 根据用户ID查询用户信息
     *
     * @param id
     * @return
     */
    User getUserById(Integer id);

    /**
     * 根据用户名查找用户列表
     *
     * @param userName
     * @return
     */
    List<User> getUserByUserName(String userName);

    /**
     * 添加用户
     *
     * @param user
     */
    void insertUser(User user);

}
