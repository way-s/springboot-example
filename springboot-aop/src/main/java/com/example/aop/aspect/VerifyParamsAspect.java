package com.example.aop.aspect;

import com.example.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mxx
 * @Description: 参数校验
 */
@Slf4j
@Aspect
@Component
public class VerifyParamsAspect {

    @Pointcut("@annotation(verifyParams)")
    public void myPointCut(VerifyParams verifyParams) {
    }

    @Around("myPointCut(verifyParams)")
    public Object myAround(ProceedingJoinPoint joinPoint, VerifyParams verifyParams) throws Throwable {

        log.info("---AOP verify START-------------------");
        // 获取请求方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        log.info("request method：{}", className + "." + methodName);

        Map<String, Object> map = getParamsMap(joinPoint, verifyParams);
        String[] checkParams = verifyParams.params();
        // 模拟校验
        for (String keyName : checkParams) {
            Object mapVal = map.get(keyName);
            if (mapVal != null) {
                int val = Integer.parseInt(String.valueOf(mapVal));
                if (val < 0) {
                    return Result.fail("参数异常");
                }
            }
        }
        Object result = joinPoint.proceed();
        log.info("---AOP verify END-------------------");
        return result;
    }

    /**
     * 获取需要校验的参数名和值
     */
    public Map<String, Object> getParamsMap(ProceedingJoinPoint joinPoint, VerifyParams verifyParams) {
        Map<String, Object> map = new HashMap<>(16);
        // 参数名
        String[] names = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
        // 参数值
        Object[] values = joinPoint.getArgs();
        String[] checkParams = verifyParams.params();
        String paramString = Arrays.toString(checkParams);
        if (checkParams.length > 0) {
            for (int i = 0; i < names.length; i++) {
                if (paramString.contains(names[i])) {
                    map.put(names[i], values[i]);
                }
            }
        }
        log.info("map toString {}", map.toString());
        return map;
    }
}
