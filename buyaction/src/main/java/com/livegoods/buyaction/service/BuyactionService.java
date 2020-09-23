package com.livegoods.buyaction.service;

import com.livegoods.commons.Results;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.service
 * @version: 1.0
 */
public interface BuyactionService {
    /**
     *
     * @param id 商品id
     * @param user  用户手机号
     * @return
     */
    Results buyAction(String id, String user);

}
