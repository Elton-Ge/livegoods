package com.livegoods.buytime.dao.impl;

import com.livegoods.buytime.dao.BuytimeDao;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.buytime.dao.impl
 * @version: 1.0
 */
@Repository
public class BuytimeDaoImpl implements BuytimeDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Date selectDate(String itemId) {
        Item item = mongoTemplate.findById(itemId, Item.class);
        return item.getBuytime();
    }
}
