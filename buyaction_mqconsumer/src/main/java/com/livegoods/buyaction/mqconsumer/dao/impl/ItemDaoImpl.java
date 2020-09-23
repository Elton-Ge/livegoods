package com.livegoods.buyaction.mqconsumer.dao.impl;

import com.livegoods.buyaction.mqconsumer.dao.ItemDao;
import com.livegoods.pojo.Item;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.mqconsumer.dao.impl
 * @version: 1.0
 */
// 商品数据访问实现
@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private MongoTemplate mongoTemplate;

    // 更新商品是否已出租字段。
    // 更新MongoDB中item集合的语法
    // db.item.update({},{"$set":{"rented":false}}, false, true);
    @Override
    public long update(String id, Boolean rented) {
        Query query = new Query();
        // 更新条件
        query.addCriteria(Criteria.where("id").is(id));
        // 更新内容
        Update update = Update.update("rented", rented);
        UpdateResult result = mongoTemplate.updateFirst(query, update, Item.class);
        return result.getModifiedCount();
    }
}
