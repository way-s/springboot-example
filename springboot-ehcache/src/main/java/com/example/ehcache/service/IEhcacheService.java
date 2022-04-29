package com.example.ehcache.service;

/**
 * @Author: mxx
 * @Title: EhcacheService
 * @Description:
 * @Date: 2022/4/29
 */
public interface IEhcacheService {
    /**
     * getById
     *
     * @param id
     * @return
     */
    Object getById(Integer id);

    /**
     * 新增或更新数据
     *
     * @param id
     * @return
     */
    Object saveOrUpdate(Integer id);

    /**
     * 清除所有缓存
     *
     * @return
     */
    Object deleteAllCache();
}
