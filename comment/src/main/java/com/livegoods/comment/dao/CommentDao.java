package com.livegoods.comment.dao;

import com.livegoods.pojo.Comment;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.comment.dao
 * @version: 1.0
 */
public interface CommentDao {
    List<Comment> findCommentByPage(Query query);

    Long countByItemId(String itemId);

    //订单评价
    int updateComment(String feelback, String orderId);

}
