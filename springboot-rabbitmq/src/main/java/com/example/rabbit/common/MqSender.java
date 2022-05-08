package com.example.rabbit.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class MqSender {

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void sendMsg(String keyName, String msg) {
        log.info("鲁迅说：{}", msg);
        rabbitTemplate.convertAndSend(keyName, msg);
    }

    public void sendMsg(String exchange, String keyName, String msg) {
        log.info("鲁迅说：{}", msg);
        rabbitTemplate.convertAndSend(exchange, keyName, msg);
    }

    public void whoSendMsg(String keyName, String author, String msg) {
        log.info("{}说：{}", author, msg);
        rabbitTemplate.convertAndSend(keyName, msg);
    }

    public void sendMsg(String keyName, Object object) {
        log.info("object：{}", object);
        rabbitTemplate.convertAndSend(keyName, object);
    }
}
