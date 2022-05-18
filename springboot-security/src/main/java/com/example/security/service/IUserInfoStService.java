package com.example.security.service;

/**
 * @Author: mxx
 * @Description:
 */
public interface IUserInfoStService {
    /**
     * 模拟登录成功返回token
     *
     * @param account
     * @return
     */
    Object createToken(String account);
}
