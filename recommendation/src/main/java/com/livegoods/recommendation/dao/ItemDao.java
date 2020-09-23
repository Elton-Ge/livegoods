package com.livegoods.recommendation.dao;

import com.livegoods.pojo.Item;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 18/9/20
 * @Description: com.livegoods.recommendation.dao
 * @version: 1.0
 */
public interface ItemDao {
    List<Item> getRecommendation(Query query);

}
