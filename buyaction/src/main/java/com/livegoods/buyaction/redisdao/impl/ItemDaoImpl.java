package com.livegoods.buyaction.redisdao.impl;

import com.livegoods.buyaction.redisdao.ItemDao;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ItemDaoImpl implements ItemDao {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Override
    public Item get(String key) {
        return (Item) redisTemplate.opsForValue().get(key);
    }
}
