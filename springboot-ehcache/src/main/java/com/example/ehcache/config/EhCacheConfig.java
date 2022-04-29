package com.example.ehcache.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author: mxx
 * @Title: EhCacheConfiguration
 * @Description:
 * @Date: 2022/4/29
 */
@Configuration
@EnableCaching
public class EhCacheConfig {

    @Bean
    public EhCacheManagerFactoryBean cacheManagerFactoryBean() {
        EhCacheManagerFactoryBean bean = new EhCacheManagerFactoryBean();
        bean.setConfigLocation(new ClassPathResource("ehcache.xml"));
        bean.setShared(true);
        return bean;
    }

//    /**
//     *  自定义缓存管理器
//     * @param bean
//     * @return
//     */
//    @Bean
//    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean) {
//        return new EhCacheCacheManager(bean.getObject());
//    }
}
