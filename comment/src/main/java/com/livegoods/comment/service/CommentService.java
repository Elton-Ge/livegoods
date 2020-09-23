package com.livegoods.comment.service;

import com.livegoods.commons.Results;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.comment.service
 * @version: 1.0
 */
public interface CommentService {
    Results findComment(String itemId,int page, int rows);

    //订单评价
    Results getfeelback(String feelback, String orderId);
}
