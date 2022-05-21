package com.example.sa.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaCheckSafe;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: mxx
 * @Description:
 */
@RestController
@RequestMapping("/acc/")
public class SaWebController {

    /**
     * 登录认证：只有登录之后才能进入该方法
     * http://localhost:8888/acc/info
     */
    @SaCheckLogin
    @RequestMapping("info")
    public String info() {
        return "查询用户信息";
    }

    /**
     * 权限认证：必须具有指定权限才能进入该方法
     * http://localhost:8888/acc/add
     */
    @SaCheckPermission("user")
    @RequestMapping("add")
    public String add() {
        return "用户增加";
    }

    /**
     * 二级认证：必须二级认证之后才能进入该方法
     * http://localhost:8888/acc/add1
     */
    @SaCheckSafe()
    @RequestMapping("add1")
    public String add1() {
        return "用户增加 - add1";
    }
}
