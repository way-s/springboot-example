package com.example.sa.config;

import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.jwt.StpLogicJwtForMixin;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: mxx
 * @Description:
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册Sa-Token的注解拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaRouteInterceptor((req, res, handler) -> {
            // 权限认证 -- 不同模块认证不同权限
            SaRouter.match("/acc/**", r -> StpUtil.checkPermission("user"));
        }))
                .addPathPatterns("/**");
    }

    /**
     * Sa-Token 整合 jwt (Mixin 混入模式)
     *
     * @return
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogicJwtForMixin();
    }
}
