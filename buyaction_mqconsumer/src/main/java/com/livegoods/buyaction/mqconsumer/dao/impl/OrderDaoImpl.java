package com.livegoods.buyaction.mqconsumer.dao.impl;

import com.livegoods.buyaction.mqconsumer.dao.OrderDao;
import com.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void insert(Order order) {
        mongoTemplate.insert(order);  //.save(order) 也可以
    }
}
