package com.example.filter.config;

import com.example.filter.filter.MyFilter;
import com.example.filter.interceptor.MyInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Resource
    private MyInterceptor myInterceptor;

    /**
     * 注册拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 配置拦截类 和 需要拦截校验的接口
        registry.addInterceptor(myInterceptor)
                .addPathPatterns("/filter/**");
    }

    /**
     * 注册过滤器
     * 在 springboot框架下过滤器有两种实现方式：
     * 1. 注解方式；
     * 2. bean注入；
     */
    @Bean
    public FilterRegistrationBean<MyFilter> registrationBean() {
        FilterRegistrationBean<MyFilter> filter = new FilterRegistrationBean<>(new MyFilter());
        filter.addUrlPatterns("/filter/*");
        filter.setName("MyFilter");
        return filter;
    }
}
