package com.livegoods.buyaction.mqconsumer.redisdao;


import com.livegoods.pojo.Item;

// 商品数据访问接口
public interface ItemDao4Redis {
    // 根据key查询缓存的商品
    Item get(String key);
    // 设置新的键值对到redis。如果key重复，则覆盖。
    boolean set(String key, Object value);
}
