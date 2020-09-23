package com.livegoods.details.dao.impl;

import com.livegoods.details.dao.DetailsDao;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.dao.impl
 * @version: 1.0
 */
@Repository
public class DetailsDaoImpl implements DetailsDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public Item selectById(String id) {
        //这两种写法都可以
        // 1
   /*     Query query = new Query();
        query.addCriteria(new Criteria().and("id").is(id));
        List<Item> itemList = mongoTemplate.find(query, Item.class);
        return itemList.get(0);*/
          //2
        Item item = mongoTemplate.findById(id, Item.class);
        return item;
    }
}
