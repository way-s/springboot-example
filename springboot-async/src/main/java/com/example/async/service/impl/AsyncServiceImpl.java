package com.example.async.service.impl;

import com.example.async.service.IAsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mxx
 * @Description:
 */
@Service
public class AsyncServiceImpl implements IAsyncService {

    public static String SMS = "0123456789";

    @Async("threadPool1")
    @Override
    public CompletableFuture<Object> getRandom() {
        Random random = new Random();
        int anInt = random.nextInt(200);
        System.out.println("anInt= " + anInt);
        return CompletableFuture.completedFuture(anInt);
    }

    @Async("threadPool1")
    @Override
    public CompletableFuture<String> sendSms(String phoneNum) {
        System.out.println("手机号= " + phoneNum);
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture(randomNum(4));
    }

    public String randomNum(Integer length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(SMS.charAt(random.nextInt(10)));
        }
        return sb.toString();
    }

}
