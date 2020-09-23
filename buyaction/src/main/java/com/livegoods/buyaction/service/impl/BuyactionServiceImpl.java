package com.livegoods.buyaction.service.impl;

import com.livegoods.buyaction.redisdao.ItemDao;
import com.livegoods.buyaction.service.BuyactionService;
import com.livegoods.commons.Results;
import com.livegoods.commons.message.LivegoodsBuyMessage;
import com.livegoods.pojo.Item;
import com.livegoods.rabbitsender.send.publisher.PublisherSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.service.impl
 * @version: 1.0
 */
@Service
public class BuyactionServiceImpl implements BuyactionService {
    @Autowired
    private ItemDao itemDao;
    @Value("${item.redis.key.prefix}")
    private String prefix;
    @Value("${item.redis.key.suffix}")
    private String suffix;
    @Autowired
    private PublisherSender publisherSender;

    @Value("${buyaction.rabbitmq.exchange}")
    private String exchange;
    @Value("${buyaction.rabbitmq.routekey}")
    private String routekey;

    @Override
    public Results buyAction(String id, String user) {
        String key= prefix + "::"+suffix+ id;
        Item item = itemDao.get(key);
        System.out.println(key);
        if (item.getRented()){
            return Results.error("手慢了，已没有库存");
        }

        LivegoodsBuyMessage livegoodsBuyMessage = new LivegoodsBuyMessage();
        livegoodsBuyMessage.setItemId(id);
        livegoodsBuyMessage.setUsername(user);

        Boolean consumerResult = publisherSender.sendMessageSync(exchange, routekey, livegoodsBuyMessage);
        if (consumerResult){
            // 购买成功
            Results result = Results.ok();
            result.setMsg("预定成功！");
            return result;
        }   else {
            // 购买成功
            Results result = Results.error();
            result.setMsg("预定失败！");
            return result;
        }
    }
}
