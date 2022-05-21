package com.example.sa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.service.IUserInfoService;
import com.example.entity.pojo.UserInfo;
import com.example.sa.service.ISaUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author: mxx
 * @Description:
 */
@Service
public class SaUserServiceImpl implements ISaUserService {

    @Resource
    private IUserInfoService userInfoService;

    @Override
    public UserInfo login(String username, String password) {
        return this.userInfoService.getOne(new LambdaQueryWrapper<UserInfo>()
                .select(UserInfo::getAccount, UserInfo::getPassword)
                .eq(UserInfo::getAccount, username)
        );
    }
}
