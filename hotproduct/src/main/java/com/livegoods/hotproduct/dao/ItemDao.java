package com.livegoods.hotproduct.dao;

import com.livegoods.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.hotproduct.dao
 * @version: 1.0
 */
public interface ItemDao {
    List<Item> getHotProduct(Query query);
}
