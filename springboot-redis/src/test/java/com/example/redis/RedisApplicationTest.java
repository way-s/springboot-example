package com.example.redis;

import com.alibaba.fastjson.JSONObject;
import com.example.common.service.INewBookStoreService;
import com.example.entity.pojo.NewBookStore;
import com.example.redis.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@SpringBootTest
public class RedisApplicationTest {

    @Resource
    INewBookStoreService bookStoreService;

    @Resource
    RedisUtil redisUtil;

    @Resource
    private RedisTemplate<Object, Object> redisTemplate;

    @Test
    public void testSet() {
        NewBookStore book = bookStoreService.getById(1);
        log.info("book= {}", book);
//        redisUtil.set("book::" + book.getId(), book, 5);
        redisTemplate.opsForValue().set("book::" + book.getId(), book, 20, TimeUnit.MINUTES);
    }

    /**
     * fastjson JSON对象 转换 Java对象
     */
    @Test
    public void testGet() {
        Object object = redisUtil.get("book::" + 1);
//        Object object = redisTemplate.opsForValue().get("book::" + 1);
        assert object != null;

        // 方案一
        // 先转成JSON对象
//        JSONObject json = (JSONObject) JSONObject.toJSON(object);
        // json 对象转换成java对象
//        NewBookStore book = JSONObject.toJavaObject(json, NewBookStore.class);

        // 方案二
        // JSON字符串转换成Java对象
        // JSONObject.toJSONString(object)：Java对象转换成 String类型的JSON字符串
        NewBookStore book = JSONObject.parseObject(JSONObject.toJSONString(object), NewBookStore.class);
        log.info("obj= {}", object);
        log.info("book= {}", book);
    }
}
