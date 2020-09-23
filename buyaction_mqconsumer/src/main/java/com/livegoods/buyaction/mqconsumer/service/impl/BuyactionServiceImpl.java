package com.livegoods.buyaction.mqconsumer.service.impl;

import com.livegoods.buyaction.mqconsumer.dao.ItemDao;
import com.livegoods.buyaction.mqconsumer.dao.OrderDao;
import com.livegoods.buyaction.mqconsumer.redisdao.ItemDao4Redis;
import com.livegoods.buyaction.mqconsumer.service.BuyactionService;
import com.livegoods.pojo.Item;
import com.livegoods.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

// 购买服务实现
@Service
public class BuyactionServiceImpl implements BuyactionService {
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private ItemDao4Redis itemDao4Redis;
    @Autowired
    private OrderDao orderDao;
    @Value("${item.redis.key.prefix}")
    private String itemPrefix;
    @Value("${item.redis.key.suffix}")
    private String itemSuffix;

    /**
     * 购买流程：
     * 0、 同步队列保证抢购的数据安全！！！
     * 1、 更新redis缓存中的商品数据。并判断更新结果。 更新成功执行后续。 原子操作。
     * 2、 更新MongoDB中的商品数据。
     * 3、 记录订单信息
     * 4、 返回处理结果
     * @param id 商品主键
     * @param user 用户手机号
     * @return
     */
    @Override
    public boolean buyaction(String id, String user) {

        String key = itemPrefix + "::" + itemSuffix  + id ;
        // 要更新的商品对象
        Item value = itemDao4Redis.get(key);
        value.setRented(true); // 设置为已出租
        // 更新redis缓存
        boolean isUpdateRedis = itemDao4Redis.set(key, value);
        if(isUpdateRedis){// 更新MongoDB
            long rows = itemDao.update(id, true);
            if(rows == 1) { // 更新了一行数据。
                // 记录订单
                Order order = new Order();
                order.setCommentState(0);
                order.setHouseType(value.getHouseType4Search());
                order.setImg(value.getImg());
                order.setItemId(value.getId());
                order.setPrice(value.getPrice().toString());
                order.setRentType(value.getRentType());
                order.setTitle(value.getTitle());
                order.setUsername(user);
                orderDao.insert(order);
                return true;
            }
        }
        // 购买失败
        return false;
    }
}
