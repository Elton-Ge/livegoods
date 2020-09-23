package com.livegoods.order.service;

import com.livegoods.pojo.Order;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.order.service
 * @version: 1.0
 */
public interface OrderService {
    List<Order> getOrder(String user);
}
