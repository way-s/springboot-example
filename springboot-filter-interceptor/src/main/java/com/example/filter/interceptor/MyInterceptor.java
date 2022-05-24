package com.example.filter.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {
    /**
     * 请求之前
     * 可以进行登录拦截，编码处理、安全控制、权限校验等处理
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        log.info("-----------------拦截器 request start-----------------");
        String requestUrl = request.getRequestURI();
        log.info("request uri:{}", requestUrl);
        log.info("request method:{}", request.getMethod());
        log.info("token:{}", token);
        log.info("-----------------拦截器 request end-----------------");
        // 放行
        return true;
    }

    /**
     * 后处理，在业务处理器处理请求执行完成后，生成视图之前被调用。即调用了Service并返回ModelAndView，
     * 但未进行页面渲染，可以修改ModelAndView，这个比较少用。
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("拦截器，后处理");
    }

    /**
     * 请求结束之后，用于清理资源等
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("拦截器，请求结束");
    }
}
