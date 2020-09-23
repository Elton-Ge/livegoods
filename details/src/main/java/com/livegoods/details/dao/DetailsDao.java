package com.livegoods.details.dao;

import com.livegoods.pojo.Item;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.dao
 * @version: 1.0
 */
public interface DetailsDao {

    Item selectById(String id);
}
