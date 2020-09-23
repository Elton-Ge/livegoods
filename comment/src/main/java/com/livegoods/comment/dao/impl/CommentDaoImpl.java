package com.livegoods.comment.dao.impl;

import com.livegoods.comment.dao.CommentDao;
import com.livegoods.pojo.Comment;
import com.livegoods.pojo.Order;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.TypedAggregation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.comment.dao.impl
 * @version: 1.0
 */
@Repository
public class CommentDaoImpl implements CommentDao {
    @Autowired
    private MongoTemplate mongoTemplate;
    @Override
    public List<Comment> findCommentByPage(Query query) {
        return mongoTemplate.find(query,Comment.class);
    }

    @Override
    public Long countByItemId(String itemId) {
        TypedAggregation<Comment> commentTypedAggregation = new TypedAggregation<Comment>(
                Comment.class, Aggregation.group("itemId").count().as("count")
        );
        AggregationResults<Document> aggregate = mongoTemplate.aggregate(commentTypedAggregation, Document.class);
        Long count = Long.parseLong(aggregate.getUniqueMappedResult().get("count").toString());

        return count;
    }

    @Override
    public int updateComment(String feelback, String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(orderId));

//        update.set("commentState",2);
        Update u1 = Update.update("feelback", feelback);
        Update u2 = Update.update("commentState",2);
        Update u3 = Update.update("test","测试字段");


        UpdateResult updateResult = mongoTemplate.updateFirst(query, u1, Order.class);
        UpdateResult updateResult1 = mongoTemplate.updateFirst(query, u2, Order.class);
        UpdateResult updateResult2 = mongoTemplate.updateFirst(query, u3, Order.class);


        return 1;
    }
}
