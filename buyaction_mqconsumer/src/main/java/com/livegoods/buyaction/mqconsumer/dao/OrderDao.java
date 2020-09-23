package com.livegoods.buyaction.mqconsumer.dao;


import com.livegoods.pojo.Order;

// 订单数据访问接口
public interface OrderDao {
    void insert(Order order);
}
