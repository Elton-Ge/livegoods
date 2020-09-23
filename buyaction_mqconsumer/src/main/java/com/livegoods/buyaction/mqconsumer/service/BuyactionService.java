package com.livegoods.buyaction.mqconsumer.service;

// 购买服务接口
public interface BuyactionService {
    /**
     * 购买商品
     * @param id 商品主键
     * @param user 用户手机号
     * @return
     */
    boolean buyaction(String id, String user);
}
