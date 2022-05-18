package com.example.security.service.impl;

import com.example.entity.vo.Result;
import com.example.security.service.IAuthService;
import com.example.security.util.JwtUtil;
import com.example.security.vo.LoginVo;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mxx
 * @Description:
 */
@Service
public class AuthServiceImpl implements IAuthService {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private CustomUserDetailsServiceImpl userDetailsService;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Object login(LoginVo loginVo) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(loginVo.getAccount());
        if (!passwordEncoder.matches(loginVo.getPassword(), userDetails.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        // 创建UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 保存认证信息
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        Map<String, Object> claims = new HashMap<>(16);
        claims.put("account", loginVo.getAccount());
        String token = jwtUtil.createToken(claims);
        return Result.success(token, "登录成功");
    }
}
