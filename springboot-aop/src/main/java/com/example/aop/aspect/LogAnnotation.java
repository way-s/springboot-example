package com.example.aop.aspect;

import java.lang.annotation.*;

/**
 * @Author: mxx
 * @Title: LogAnnotation
 * @Description:
 * @Date: 2022/4/26
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME) //注解在哪个阶段执行
@Documented
public @interface LogAnnotation {
    /**
     * 操作模块
     */
    String module() default "";

    /**
     * 操作说明
     */
    String operaDesc() default "";
}
