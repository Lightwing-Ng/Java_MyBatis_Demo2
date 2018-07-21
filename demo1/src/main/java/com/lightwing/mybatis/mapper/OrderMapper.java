package com.lightwing.mybatis.mapper;

import com.lightwing.mybatis.pojo.Order;
import com.lightwing.mybatis.pojo.OrderUser;

import java.util.List;

/**
 * 订单持久化接口
 *
 * @author Steven
 */
public interface OrderMapper {

    /**
     * 获取订单列表
     *
     * @return
     */
    List<Order> getOrderList();

    /**
     * ResultMap使用
     *
     * @return
     */
    List<Order> getOrderListMap();

    /**
     * 一对一关联：resultType使用
     *
     * @return
     */
    List<OrderUser> getOrderUser();

    /**
     * 一对一关联：ReaultMap使用
     *
     * @return
     */
    List<Order> getOrderUserMap();
}
