package com.example.ehcache.service.impl;

import com.example.common.service.INewBookStoreService;
import com.example.ehcache.service.IEhcacheService;
import com.example.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * @Author: mxx
 * @Title: EhcacheServiceImpl
 * @Description:
 * @Date: 2022/4/29
 */
@Slf4j
@Service
//@CacheConfig(cacheNames = "{myCache}")
public class EhcacheServiceImpl implements IEhcacheService {

    @Resource
    private INewBookStoreService bookStoreService;

    /**
     * Cacheable缓存数据，如果缓存中有数据，从缓存中获取，否则从方法中获取，并更新到缓存
     */
    @Cacheable(value = "myCache", key = "#id")
    @Override
    public Object getById(Integer id) {
        log.info("查数据库...");
        return this.bookStoreService.getById(id);
    }

    /**
     * CachePut 更新缓存
     */
    @CachePut(value = "myCache", key = "'save'+#id")
    @Override
    public Object saveOrUpdate(Integer id) {
        //模拟 update
        log.info("更新了数据库，id= {}", id);
        String data = "id:" + id + " date:" + LocalDateTime.now();
        return Result.success(data, "请求成功");
    }

    /**
     * allEntries 所有的缓存
     */
    @CacheEvict(value = "myCache", allEntries = true)
    @Override
    public Object deleteAllCache() {
        log.info("清除所有缓存");
        return Result.success("删除成功");
    }

}

