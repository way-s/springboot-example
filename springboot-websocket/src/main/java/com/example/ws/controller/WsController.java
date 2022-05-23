package com.example.ws.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Controller
public class WsController {
    /**
     * http://localhost:8888/hello
     *
     * @return
     */
    @RequestMapping("/hello")
    public String hello() {
        log.info("跳转hello页面");
        return "hello";
    }
}
