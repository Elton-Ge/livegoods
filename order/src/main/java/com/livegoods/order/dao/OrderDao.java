package com.livegoods.order.dao;

import com.livegoods.pojo.Order;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.order.dao
 * @version: 1.0
 */
public interface OrderDao {
    List<Order> findOrder(String user);
}
