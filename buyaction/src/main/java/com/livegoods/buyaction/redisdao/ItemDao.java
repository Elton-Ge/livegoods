package com.livegoods.buyaction.redisdao;


import com.livegoods.pojo.Item;

public interface ItemDao {
    // 根据key查询value。 根据key查询缓存中的商品对象。
    Item get(String key);
}
