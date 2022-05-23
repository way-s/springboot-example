package com.example.ws.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @Author: mxx
 * @Description: - @ServerEndpoint 用于配置websocket的地址    ws：//127.0.0.1:8080/websocket
 */
@Slf4j
@ServerEndpoint(value = "/websocket")
@Component
public class WebSocketServer {
    /**
     * concurrent包的线程安全Set，每个客户端的 websocket连接会创建一个WebSocketServer对象。
     */
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<>();

    /**
     * 这个session是每个 websocket 与客户端的连接会话，通过它与客户端进行数据交互
     */
    private Session session;

    /**
     * webSocket 连接成功之后调用的方法
     */
    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("有新连接加入！sessionId: {}", session.getId());
        try {
            sendMessage("链接成功！");
        } catch (IOException e) {
            e.printStackTrace();
            log.error("发送失败，原因是：{}", e.getMessage());
        }
    }

    /**
     * 关闭 webSocket 连接调用的方法
     */
    @OnClose
    public void onClose() {
        // 从set中删除
        webSocketSet.remove(this);
        log.warn("有连接关闭，sessionId为：{}", session.getId());
    }

    /**
     * 收到客户端发来的消息调用的方法
     *
     * @param message 客户端发来的消息载体
     */
    @OnMessage
    public void onMessage(String message) {
        log.info("客户端发来的消息：{}", message);
        // 群发
        //将接受到的消息群发给所有websocket连接。这里也可以根据实际需求修改代码，将接受到的信息发送给某个指定的websocket连接。
        for (WebSocketServer item : webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 发生错误时调用
     */
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
        log.error("ws 发生错误，sessionId为：{}，原因是：{}", session.getId(), error.getMessage());
    }

    /**
     * 服务端发送消息给客户端
     */
    private void sendMessage(String meg) throws IOException {
        session.getBasicRemote().sendText(meg);
    }
}
