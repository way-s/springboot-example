package com.example.security.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateUtil;
import com.example.security.entity.JwtConfigure;
import com.example.security.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

/**
 * @Author: mxx
 * @Description:
 */
@Slf4j
@Component
public class JwtUtil {

//    @Value("${jwt.secret}")
//    private String secret;
//
//    @Value("${jwt.expiration}")
//    private Integer expiration;

    @Resource
    private JwtConfigure jwtConfigure;

    /**
     * 创建token
     *
     * @param claims
     * @return
     */
    public String createToken(Map<String, Object> claims) {
        return Jwts.builder()
                // 签名算法 公钥
                .signWith(SignatureAlgorithm.HS256, jwtConfigure.getSecret())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(DateUtil.offsetHour(new Date(), jwtConfigure.getExpiration()))
                .compact();
    }

    /**
     * 获取token中的id
     *
     * @param token
     * @return
     */
    public Long getUserIdFromToken(String token) {
        Object id = getTokenBody(token).get("id");
        return Long.valueOf(id.toString());
    }

    /**
     * 验证token是否还有效
     *
     * @param token       客户端传入的token
     * @param userDetails 从数据库或者缓存中查询出来的用户信息
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserFromToken(token, User::getAccount);
        return username.equals(userDetails.getUsername()) && !isExpiration(token);
    }

    /**
     * 获取token中的信息
     *
     * @param token
     * @param claimsResolver
     * @param <T>
     * @return
     */
    public <T> T getUserFromToken(String token, Function<User, T> claimsResolver) {
        final Claims claims = getTokenBody(token);
        if (claims == null) {
            return null;
        }
        User user = Convert.convert(User.class, claims);
        return claimsResolver.apply(user);
    }

    /**
     * 是否过期
     *
     * @param token
     * @return
     */
    public boolean isExpiration(String token) {
        return getTokenBody(token).getExpiration().before(new Date());
    }

    public Claims getTokenBody(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(jwtConfigure.getSecret())
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.error("解析token 错误，token过期或不正确: {}", token);
        }
        return claims;
    }

}
