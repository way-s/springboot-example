package com.example.rabbit.controller;

import com.example.rabbit.common.Constants;
import com.example.rabbit.common.MqSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@RestController
public class WebController {

    @Resource
    private MqSender mqSender;

    /**
     * http://localhost:8888/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String testHello() {
        mqSender.sendMsg(Constants.SIMPLE_QUEUE_KEY,"吸烟有害健康");
        return "ok";
    }
}
