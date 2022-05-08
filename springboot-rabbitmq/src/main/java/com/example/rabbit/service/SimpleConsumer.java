package com.example.rabbit.service;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @Author: mxx
 * @Description: 消费者
 */
@Slf4j
@Component
//@RabbitListener(queuesToDeclare = @Queue("hello-t"))
public class SimpleConsumer {

    /**
     * queues：监听的队列名称
     * queuesToDeclare：监听的队列Queue注解对象
     *
     * @param msg
     */
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("hello-t"))
    public void receive1(String msg) {
        log.info("鲁迅的学生们听说：{}", msg);
    }

    /**
     * @param mg      @Payload 获取载荷信息，消息中的 body
     * @param message
     * @param channel
     * @throws IOException
     */
    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("hello-t-1"))
    public void receive2(@Payload String mg, Message message, Channel channel) throws IOException {
        try {
//            int i = 1 / 0;

            log.info("鲁迅的学生们听说：{}", new String(message.getBody()));
            log.info("message：{}", message.toString());
            log.info("body：{}", mg);

            // multiple 取值为 false 时，表示通知 RabbitMQ 当前消息被确认
            // 如果为 true，则额外将比第一个参数指定的 delivery tag 小的消息一并确认
            boolean multiple = false;
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), multiple);
        } catch (Exception e) {
            // 两个布尔值  第二个设为 false 则丢弃该消息 设为true 则返回给队列
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}
