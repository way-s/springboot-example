package com.example.ehcache.controller;

import com.example.ehcache.service.IEhcacheService;
import com.example.entity.vo.Result;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Random;

/**
 * @Author: mxx
 * @Title: EhcacheController
 * @Description:
 * @Date: 2022/4/29
 */
@RestController
@RequestMapping("/ehcache")
public class EhcacheController {

    @Resource
    private IEhcacheService ehcacheService;

    /**
     * http://localhost:8888/ehcache/r
     * Cacheable缓存数据，如果缓存中有数据，从缓存中获取，否则从方法中获取，并更新到缓存
     *
     * @return
     */
    @Cacheable(value = "myCache", key = "'random'")
    @GetMapping("/r")
    public Object getRandom() {
        Random random = new Random();
        int anInt = random.nextInt(200);
        System.out.println("anInt= " + anInt);
        return Result.success(anInt);
    }

    /**
     * http://localhost:8888/ehcache/1
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {
        Object data = this.ehcacheService.getById(id);
        return Result.success(data);
    }

    /**
     * http://localhost:8888/ehcache/update/1
     *
     * @param id
     * @return
     */
    @GetMapping("/update/{id}")
    public Object saveOrUpdate(@PathVariable Integer id) {
        return this.ehcacheService.saveOrUpdate(id);
    }

    /**
     * 清除所有缓存
     * http://localhost:8888/ehcache/del
     *
     * @return
     */
    @GetMapping("/del")
    public Object deleteAllCache() {
        return this.ehcacheService.deleteAllCache();
    }

}
