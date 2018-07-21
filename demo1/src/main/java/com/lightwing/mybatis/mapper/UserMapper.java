package com.lightwing.mybatis.mapper;

import com.lightwing.mybatis.pojo.QueryVo;
import com.lightwing.mybatis.pojo.User;

import java.util.List;

/**
 * 用户信息持久化接口
 *
 * @author Steven
 */
public interface UserMapper {

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

    /**
     * 传递包装pojo
     *
     * @param vo
     * @return
     */
    List<User> getUserByQueryVo(QueryVo vo);

    /**
     * 查询用户总记录数
     *
     * @return
     */
    Integer getUserCount();

    /**
     * 演示-if标签的使用
     *
     * @param user
     * @return
     */
    List<User> getUserByPojo(User user);

    /**
     * 演示-foreach标签的使用，跟据用户id列表查询用户
     *
     * @param vo
     * @return
     */
    List<User> getUserByIds(QueryVo vo);

    /**
     * 演示一对多关联查询-ReaultMap
     *
     * @return
     */
    List<User> getUserOrderMap();

}
