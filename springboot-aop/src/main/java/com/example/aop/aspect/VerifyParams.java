package com.example.aop.aspect;

import java.lang.annotation.*;

/**
 * @Author: mxx
 * @Description: 参数校验
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VerifyParams {
    /**
     * 需要校验的参数
     */
    String[] params() default {"size"};
}
