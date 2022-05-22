package com.example.sa.component;

import cn.dev33.satoken.exception.DisableLoginException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import com.example.entity.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理未登录的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotLoginException.class)
    public Object handleNotLoginException(NotLoginException e) {
        log.error("NotLoginException: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 处理没有权限的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotPermissionException.class)
    public Object handleNotPermissionException(NotPermissionException e) {
        log.error("NotPermissionException: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 处理没有角色的异常
     */
    @ResponseBody
    @ExceptionHandler(value = NotRoleException.class)
    public Object handleNotRoleException(NotRoleException e) {
        log.error("NotRoleException: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

    /**
     * 处理被封禁异常
     */
    @ResponseBody
    @ExceptionHandler(value = DisableLoginException.class)
    public Object handleNotRoleException(DisableLoginException e) {
        log.error("DisableLoginException: {}", e.getMessage());
        return Result.fail(e.getMessage());
    }

}
