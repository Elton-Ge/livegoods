package com.livegoods.buyaction.mqconsumer.dao;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.mqconsumer.dao
 * @version: 1.0
 */
public interface ItemDao {
    // 更新商品数据， 是否已出租字段。
    long update(String id, Boolean rented);
}
