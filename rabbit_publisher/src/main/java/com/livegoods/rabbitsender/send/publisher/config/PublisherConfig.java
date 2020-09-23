package com.livegoods.rabbitsender.send.publisher.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.rabbitsender.send.config
 * @version: 1.0
 */
@Configuration
public class PublisherConfig {
    @Value("${buyaction.rabbitmq.exchange}")
    private String exchange;
    @Value("${buyaction.rabbitmq.routekey}")
    private String routekey;
    @Value("${buyaction.rabbitmq.queue}")
    private String itemQueue;

    @Bean
    public Queue itemQueue(){
        return  new Queue(itemQueue);
    }

    @Bean
    public DirectExchange directExchange(){
        return  new DirectExchange(exchange);
    }
    @Bean
    public Binding contentBinding(Queue contentQueue, DirectExchange directExchange ){
        return BindingBuilder.bind(contentQueue).to(directExchange).with(routekey);
    }
}
