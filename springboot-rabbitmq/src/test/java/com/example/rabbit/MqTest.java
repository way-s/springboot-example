package com.example.rabbit;

import com.example.rabbit.common.Constants;
import com.example.rabbit.common.MqSender;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mxx
 * @Description:
 */
@SpringBootTest
public class MqTest {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Resource
    private MqSender mqSender;

    //    -------- hello 模式，点对点消费 --------
    @Test
    public void testHello() throws InterruptedException {
        String msg = "吸烟有害健康";
        // 发送消息
//        mqSender.sendMsg(Constants.SIMPLE_QUEUE_KEY, msg);
        // 手动应答
        mqSender.sendMsg(Constants.SIMPLE_QUEUE_KEY_1, msg);
        // 等一会
        TimeUnit.MILLISECONDS.sleep(50);
//        rabbitTemplate.convertAndSend("hello-t", msg);
    }

    //    -------- work 模式，资源的竞争 --------
    @Test
    public void testWork() throws InterruptedException {
        String msg = "我一般不抽烟，除非忍不住 ";
        for (int i = 1; i <= 6; i++) {
            // 发送消息
            mqSender.sendMsg(Constants.WORK_ROUTING_KEY, msg + i);
        }
        TimeUnit.MILLISECONDS.sleep(50);
    }

    //    -------- 发布订阅 fanout 模式，共享资源 --------
    @Test
    public void testFanout() throws InterruptedException {
        String msg = "我向来不以最坏的恶意揣测家门口对面的烟商 ";
        for (int i = 1; i <= 4; i++) {
            // 发送消息
            mqSender.sendMsg(Constants.FANOUT_EXCHANGE, "", msg + i);
        }
        TimeUnit.MILLISECONDS.sleep(50);
    }

    //    -------- Routing 静态路由模式 --------
    @Test
    public void testRouting() throws InterruptedException {
        String msg = "我心里想着两个人，一个是他，另一个还是他。 ";
        for (int i = 1; i <= 4; i++) {
            // 发送消息
            mqSender.sendMsg(Constants.ROUTE_EXCHANGE, Constants.ROUTE_ROUTING_KEY, msg + i);
        }
        TimeUnit.MILLISECONDS.sleep(50);
    }

    //    -------- Topic 动态路由模式，支持模糊匹配 --------
    @Test
    public void testTopic() throws InterruptedException {
        String msg = "啊，有烟抽的感觉，真好。 ";
        for (int i = 1; i <= 4; i++) {
            if (i <= 2) {
                mqSender.sendMsg(Constants.TOPIC_EXCHANGE, Constants.TOPIC_ROUTING_KEY, msg + i);
            } else {
                // 使用配置类
                mqSender.sendMsg(Constants.TOPIC_EXCHANGE, Constants.TOPIC_ROUTING_KEY_1, msg + i);
            }
        }
        TimeUnit.MILLISECONDS.sleep(50);
    }

}
