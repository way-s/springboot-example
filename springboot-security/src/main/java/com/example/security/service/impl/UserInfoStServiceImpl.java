package com.example.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.UserInfo;
import com.example.entity.vo.Result;
import com.example.security.service.IUserInfoStService;
import com.example.security.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Service
public class UserInfoStServiceImpl implements IUserInfoStService {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private IUserInfoService userInfoService;

    @Override
    public Object createToken(String account) {
        UserInfo user = userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .eq(UserInfo::getAccount, account)
        );
        if (user == null) {
            return Result.fail();
        }
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("username", user.getNickName());
        claims.put("account", user.getAccount());
        String token = jwtUtil.createToken(claims);

        log.info("create token = {}", token);
        return Result.success((Object) token);
    }
}
