package com.example.security;

import com.example.common.service.ISysRoleService;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.SysRole;
import com.example.entity.pojo.UserInfo;
import com.example.security.entity.User;
import com.example.security.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@SpringBootTest
public class SJwtTest {

    @Resource
    private IUserInfoService userInfoService;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private ISysRoleService sysRoleService;

    // ----------jwt----------

    @Test
    public void testGetToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", "whale");
        claims.put("account", "123");
        String token = jwtUtil.createToken(claims);

        log.info("create token = {}", token);
    }

    @Test
    public void testParseToken() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6" +
                "IjEyMzEyMyIsImV4cCI6MTY1MjU1NDgwMiwi" +
                "aWF0IjoxNjUyNTMzMjAyLCJ1c2VybmFtZSI6" +
                "IndoYWxlIn0.heIkGrlhruId1zl9Bko_jH_do-LIp-JlQGVjaGvXhxY";
        Long id1 = jwtUtil.getUserIdFromToken(token);
        log.info("id1 = {}", id1);

        Claims tokenBody = jwtUtil.getTokenBody(token);
        log.info("tokenBody = {}", tokenBody);
        Long id = jwtUtil.getUserFromToken(token, User::getId);
        log.info("id = {}", id);
    }


    /**
     * 添加用户
     */
    @Test
    public void testInsert() {
        UserInfo user = new UserInfo()
                .setNickName("阿朱")
                .setAccount("106")
                .setPassword("123")
                .setEmail("@1231127xa.com")
                .setLocation("衡阳");

        userInfoService.save(user);
        log.info("user: {}", user);

        SysRole role = new SysRole()
                .setRole("user")
                .setCreateBy(1525504710046396418L)
                .setUpdateBy(1525504710046396418L)
                .setRoleId(user.getId())
                .setStatus("0");
        sysRoleService.save(role);
        log.info("role: {}", role);
    }

}
