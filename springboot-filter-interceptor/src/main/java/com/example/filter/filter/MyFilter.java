package com.example.filter.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
// @WebFilter(urlPatterns = { "需要过滤的url" }, filterName = "过滤器的名称")
//@WebFilter(filterName = "MyFilter", urlPatterns = {"/*"})
public class MyFilter implements Filter {
    /**
     * 初始化
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("------过滤器初始化------");
    }

    /**
     * 可在filter中处理跨域问题
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("-----------------过滤器，开始过滤-----------------");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestUrl = request.getRequestURI();
        log.info("request uri:{}", requestUrl);
        log.info("request method:{}", request.getMethod());
        log.info("-----------------过滤器，结束过滤-----------------");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * 停止服务器时调用destroy()方法，销毁实例
     */
    @Override
    public void destroy() {
        log.info("------过滤器销毁------");
    }
}
