package com.example.async.service;

import java.util.concurrent.CompletableFuture;

/**
 * @Author: mxx
 * @Description:
 */
public interface IAsyncService {
    /**
     * 获取一个随机数
     *
     * @return
     */
    CompletableFuture<Object> getRandom();

    /**
     * 发送短信验证码
     *
     * @param phoneNum
     * @return
     */
    CompletableFuture<String> sendSms(String phoneNum);
}
