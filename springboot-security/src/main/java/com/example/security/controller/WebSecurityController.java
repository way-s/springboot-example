package com.example.security.controller;

import com.example.entity.vo.Result;
import com.example.security.entity.UserDetail;
import com.example.security.service.IAuthService;
import com.example.security.service.IUserInfoStService;
import com.example.security.vo.LoginVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/st")
public class WebSecurityController {

    @Resource
    private IUserInfoStService userInfoStService;

    @Resource
    private IAuthService authService;

    /**
     * http://localhost:8888/st/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public Object hello() {
        log.info("hello 接口被访问");
        return Result.success("ok");
    }

    /**
     * http://localhost:8888/st/login
     * 登录
     *
     * @return
     */
    @GetMapping("/login")
    public Object login(@RequestBody LoginVo loginVo) {
        log.info("login 接口被访问");
        return this.authService.login(loginVo);
    }

    /**
     * http://localhost:8888/st/token/102
     *
     * @return
     */
    @GetMapping("/token/{account}")
    public Object getToken(@PathVariable String account) {
        log.info("模拟登录成功并返回token account: {}", account);
        return this.userInfoStService.createToken(account);
    }

    /**
     * http://localhost:8888/st/admin
     *
     * @return
     */
    @GetMapping("/admin")
    public Object testAdmin() {
        log.info("admin 接口被访问");
        return Result.success("admin");
    }

    /**
     * http://localhost:8888/st/user
     *
     * @return
     */
    @GetMapping("/user")
    public Object testUser() {
        log.info("user 接口被访问");
        return Result.success("user");
    }

    /**
     * 获取系统用户
     * http://localhost:8888/st/pal
     *
     * @return
     */
    @GetMapping("/pal")
    public Object testGet() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return Result.fail();
        }
        log.info("authentication: {}", authentication);
        UserDetail principal = (UserDetail) authentication.getPrincipal();
        return Result.success(principal);
    }

}


