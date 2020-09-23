package com.livegoods.buyaction.config;

import com.livegoods.rabbitsender.send.publisher.PublisherSender;
import com.livegoods.redis.config.RedisCacheConfiguration;
import org.reactivestreams.Publisher;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.config
 * @version: 1.0
 */
@Configuration
public class RedisConfig extends RedisCacheConfiguration {
    @Bean
    @Override
    protected RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return super.redisTemplate(redisConnectionFactory);
    }
//    @Bean
//    public PublisherSender livegoodsMessagePublisher(AmqpTemplate amqpTemplate){
//        PublisherSender messagePublisher = new PublisherSender();
//        messagePublisher.setAmqpTemplate(amqpTemplate);
//        return messagePublisher;
//    }
}
