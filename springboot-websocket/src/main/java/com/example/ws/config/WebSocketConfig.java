package com.example.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: mxx
 * @Description: - @EnableWebSocket 开启 Websocket的支持
 * - @EnableWebSocketMessageBroker   websocket使用STOMP协议进行消息的传递  还需要进行额外的配置不然会报错
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig {
    /**
     * 自动注册使用 @ServerEndpoint 注解声明的 websocket endpoint
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }
}
