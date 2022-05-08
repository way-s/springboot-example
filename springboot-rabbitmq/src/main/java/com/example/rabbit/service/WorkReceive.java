package com.example.rabbit.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class WorkReceive {

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("work-1"))
    public void receiveMsg1(String msg) {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
        log.info("work-1 在 {}的时候听说：{}", date, msg);
    }

    @RabbitHandler
    @RabbitListener(queuesToDeclare = @Queue("work-1"))
    public void receiveMsg2(String msg) {
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        log.info("work-2 在 {}的时候听说：{}", date, msg);
    }
}
