package com.example.sa.controller;

import cn.dev33.satoken.stp.SaLoginConfig;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.example.entity.pojo.UserInfo;
import com.example.sa.service.ISaUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@RestController
@RequestMapping("/user/")
public class SaUserController {

    @Resource
    private ISaUserService userService;

    /**
     * 测试登录，浏览器访问： http://localhost:8888/user/doLogin?username=102&password=123
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("doLogin")
    public Object doLogin(@RequestParam String username, @RequestParam String password) {
        log.info("username: {}, password: {}", username, password);
        // 此处仅作模拟示例，真实项目需要从数据库中查询数据进行比对
//        if ("102".equals(username) && "123".equals(password)) {
        UserInfo user = userService.login(username, password);
        if (user != null) {
            StpUtil.login(user.getAccount(), SaLoginConfig
                    .setExtra("username", username)
                    .setExtra("password", password));
            return SaResult.data(StpUtil.getTokenInfo().getTokenValue());
        }
        return SaResult.error("登录失败");
    }

    /**
     * 查询登录状态，浏览器访问： http://localhost:8888/user/isLogin
     *
     * @return
     */
    @GetMapping("isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

    /**
     * 查询 Token 信息  ---- http://localhost:8888/user/tokenInfo
     *
     * @return
     */
    @RequestMapping("tokenInfo")
    public SaResult tokenInfo() {
        return SaResult.data(StpUtil.getTokenInfo());
    }

    /**
     * 测试注销  ---- http://localhost:8888/user/logout
     *
     * @return
     */
    @RequestMapping("logout")
    public SaResult logout() {
        log.info("logout: {}", StpUtil.getLoginId());
        StpUtil.logout();
        return SaResult.ok();
    }

    /**
     * http://localhost:8888/user/hello
     *
     * @return
     */
    @GetMapping("/hello")
    public SaResult hello() {
        return SaResult.ok();
    }

}
