package com.livegoods.details.config;

import com.livegoods.redis.config.RedisCacheConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.config
 * @version: 1.0
 */
@Configuration
public class DetailsConfig extends RedisCacheConfiguration {
    @Override
    @Bean
    protected CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        return super.cacheManager(redisConnectionFactory);
    }
}
