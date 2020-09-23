package com.livegoods.comment.service.impl;

import com.livegoods.comment.dao.CommentDao;
import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.Results;
import com.livegoods.pojo.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.comment.service.impl
 * @version: 1.0
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Override
    public Results findComment(String itemId,int page, int rows) {
        long count = commentDao.countByItemId(itemId);

        Query query = new Query();
        //查询条件
        query.addCriteria(new Criteria("itemId").is(itemId));
        query.with(PageRequest.of(page,rows));    //分页

        List<Comment> commentByPage = commentDao.findCommentByPage(query);
        System.out.println(commentByPage);
        Results results = Results.ok(commentByPage);
        long l = (count % rows == 0) ?( count / rows ): (count / rows + 1);

        if (page+1<l){
            results.setHasMore(true);
        }else {
            results.setHasMore(false);
        }


        // 隐藏用户手机号。 把字符串的135 1234 4321 -> 135 **** 4321
        for(Comment comment : commentByPage){
            String username = comment.getUsername()
                    .replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
            comment.setUsername(username);
        }
        return results;
    }

    @Override
    public Results getfeelback(String feelback, String orderId) {
        int i = commentDao.updateComment(feelback, orderId);
        if (i==1){
            Results results = Results.ok();
            results.setMsg("评论成功");
            return results;
        }
        Results results = Results.error();
        results.setMsg("评论失败");
        return results;
    }
}
