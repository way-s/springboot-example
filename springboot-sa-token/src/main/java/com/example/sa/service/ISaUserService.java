package com.example.sa.service;

import com.example.entity.pojo.UserInfo;

/**
 * @Author: mxx
 * @Description:
 */
public interface ISaUserService {
    /**
     * login
     *
     * @param username
     * @param password
     * @return
     */
    UserInfo login(String username, String password);
}
