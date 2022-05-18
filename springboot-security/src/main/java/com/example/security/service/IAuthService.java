package com.example.security.service;

import com.example.security.vo.LoginVo;

/**
 * @Author: mxx
 * @Description:
 */
public interface IAuthService {
    /**
     * 登录
     *
     * @param loginVo
     * @return
     */
    Object login(LoginVo loginVo);
}
