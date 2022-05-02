package com.example.async;

import com.example.async.service.IAsyncService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @Author: mxx
 * @Description:
 */
@SpringBootTest
public class AsyncApplicationTest {

    @Resource
    private IAsyncService iAsyncService;

    /**
     * CompletableFuture  get()
     * <p>
     * join()和get()方法都是用来获取CompletableFuture异步之后的返回值（等待异步任务的返回值）；
     * get方法返回结果，抛出的是检查异常，必须用户throw或者try/catch处理，join返回结果，抛出未检查异常。
     */
    @Test
    public void testAsync1() {
        System.out.println("启动");
        CompletableFuture<Object> random = iAsyncService.getRandom();
        try {
            System.out.println("异步有返回值= " + random.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * CompletableFuture join()方法
     */
    @Test
    public void testAsync2() {
        System.out.println("已发送短信");
        String sms = iAsyncService.sendSms("123131312").join();
        System.out.println("验证码 = " + sms);
    }
}
