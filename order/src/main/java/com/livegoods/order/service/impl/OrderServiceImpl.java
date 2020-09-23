package com.livegoods.order.service.impl;

import com.livegoods.order.dao.OrderDao;
import com.livegoods.order.service.OrderService;
import com.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.order.service.impl
 * @version: 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    @Override
    public List<Order> getOrder(String user) {
        List<Order> order = orderDao.findOrder(user);
        return order;
    }
}
