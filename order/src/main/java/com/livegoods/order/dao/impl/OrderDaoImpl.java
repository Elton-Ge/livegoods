package com.livegoods.order.dao.impl;

import com.livegoods.order.dao.OrderDao;
import com.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.order.dao.impl
 * @version: 1.0
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Order> findOrder(String user) {
        Query query = new Query();
        query.addCriteria(Criteria.where("username").is(user));
        List<Order> orders = mongoTemplate.find(query, Order.class);
        return orders;
    }
}
