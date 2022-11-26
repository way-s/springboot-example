package com.example.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: mxx
 * @Title: LogAspect
 * @Description:
 * @Date: 2022/4/26
 */
@Aspect
@Slf4j
@Component
public class LogAspect {

    /**
     * 配置切点，主要用于同类其他方法以此作为切入点
     * annotation 表示标注了某个注解的所有方法
     * execution：在方法执行是触发；*：返回任意类型；包路径；*：任意类；*(..)：任意参数
     * Pointcut("execution(* com.example.aop.controller.*.*(..))")
     * 执行顺序：around > before > around > after > afterReturning
     */
    @Pointcut("@annotation(com.example.aop.aspect.LogAnnotation)")
//    @Pointcut("execution(* com.example.aop.controller.*.*(..))")
    public void myPointCut() {
    }

    /**
     * before(前置通知)： 在方法开始执行前执行
     */
    @Before("myPointCut()")
    public void beforeAdvice() {
        log.info("beforeAdvice...");
    }

    /**
     * after(后置通知)： 在方法执行后执行
     */
    @After("myPointCut()")
    public void afterAdvice() {
        System.out.println("afterAdvice...");
    }

    /**
     * 环绕通知：执行调用前和调用后的任务
     *
     * @param joinPoint 表示可以执行目标方法
     * @return object
     * 必须接收一个参数，类型为ProceedingJoinPoint
     * 必须
     */
    @Around("myPointCut()")
    public Object myAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        // 获取自定义注解里的值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        log.info("---AOP LOG START-------------------");
        log.info("module：{},`\t`operaDesc：{}", logAnnotation.module(), logAnnotation.operaDesc());

        // 获取请求方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        log.info("request method：{}", className + "." + methodName);

        //获取入参
        Object[] paramsObj = joinPoint.getArgs();
        log.info("paramsObj:{}", Arrays.toString(paramsObj));
        // 执行当前目标方法
        Object result = joinPoint.proceed();
        log.info("返回参数: " + result.toString());

        long endTime = System.currentTimeMillis();
        log.info("方法用时time= {}", (endTime - startTime) + "毫秒");
        log.info("---AOP LOG END-------------------");
        return result;
    }

    /**
     * 方法执行结束，增强处理
     */
    @AfterReturning(returning = "ret", pointcut = "myPointCut()")
    public void doAfterReturning(Object ret) {
        // 处理完请求，返回内容
        System.out.println("AfterReturning：方法的返回值 : " + ret);
    }

    /**
     * 后置异常通知
     */
    @AfterThrowing("myPointCut()")
    public void afterThrowing(JoinPoint joinPoint) {
        System.err.println("AfterThrowing：方法异常时执行.....");
    }
}
