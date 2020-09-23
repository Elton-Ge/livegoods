package com.livegoods.hotproduct.dao.impl;

import com.livegoods.hotproduct.dao.ItemDao;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.hotproduct.dao.impl
 * @version: 1.0
 */
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Item> getHotProduct(Query query) {
        return mongoTemplate.find(query,Item.class);
    }
}
