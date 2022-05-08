package com.example.rabbit.config;

import com.example.rabbit.common.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: mxx
 * @Description:
 */
@Configuration
public class RabbitBindingConfig {

    // 创建 topic 交换机

    /**
     * 创建交换机
     *
     * @return
     */
    public TopicExchange topicExchange1() {
        return new TopicExchange(Constants.TOPIC_EXCHANGE);
    }

    /**
     * 创建队列
     *
     * @return
     */
    @Bean
    public Queue topicQueue1() {
        //配置 一个 Queue队列
        return new Queue(Constants.TOPIC_QUEUE_NAME_2);
    }

    /**
     * 绑定 交换机与队列
     *
     * @return
     */
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(topicQueue1()).to(topicExchange1()).with("*.max.*");
    }

}
